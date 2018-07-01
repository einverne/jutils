package com.jutils.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 反序列化时会破坏单例，定义 readRosolve 方法可解决
 *
 * 对象的序列化过程通过ObjectOutputStream和ObjectInputputStream来实现
 *
 * <pre>
 * Object obj;
 * try {
 * obj = desc.isInstantiable() ? desc.newInstance() : null;
 * } catch (Exception ex) {
 * throw (IOException) new InvalidClassException(desc.forClass().getName(),"unable to create instance").initCause(ex);
 * }</pre>
 *
 * 序列化会通过反射调用无参数的构造方法创建一个新的对象。
 * <ul>
 * <li>isInstantiable：如果一个serializable/externalizable的类可以在运行时被实例化，那么该方法就返回true</li>
 * <li>desc.newInstance：该方法通过反射的方式调用无参构造方法新建一个对象</li>
 * </ul>
 *
 * hasReadResolveMethod:如果实现了serializable 或者 externalizable接口的类中包含readResolve则返回true
 *
 * invokeReadResolve:通过反射的方式调用要被反序列化的类的readResolve方法。
 */
public class SingletonSerializable implements Serializable {
    private volatile static SingletonSerializable instance;

    private SingletonSerializable() {
        System.out.println("instance created");
    }

    public static SingletonSerializable getInstance() {
        if (instance == null) {
            synchronized (SingletonSerializable.class) {
                if (instance == null) {
                    instance = new SingletonSerializable();
                }
            }
        }
        return instance;
    }

    /**
     * 定义该方法解决反序列化破坏单例情况
     */
    private Object readResolve() {
        return instance;
    }

    public static void main(String[] args) {
        ObjectOutputStream outputStream;
        SingletonSerializable instance = SingletonSerializable.getInstance();
        String fileName = "temp";
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(instance);
            File file = new File(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            SingletonSerializable instance1 = (SingletonSerializable) objectInputStream.readObject();
            System.out.println(instance == instance1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
