package com.jutils.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 这种写法仍然使用JVM本身机制保证了线程安全问题；由于 SingletonHolder 是私有的，
 * 除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；
 * 同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖 JDK 版本。
 */
public class SingletonStaticNested {
    private static boolean flag = false;

    private static class SingletonHolder {
        private static final SingletonStaticNested instance = new SingletonStaticNested();
    }

    private SingletonStaticNested() {
        synchronized (SingletonStaticNested.class) {
            if (!flag) {
                flag = !flag;
            } else {
                throw new RuntimeException("不允许创建第二个实例");
            }
        }
        System.out.println("instance created");
    }

    public static SingletonStaticNested getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        SingletonStaticNested instance = SingletonStaticNested.getInstance();
        SingletonStaticNested instance1 = SingletonStaticNested.getInstance();

        // 通过反射获取构造函数，setAccessible(true) 调用构造函数构造新实例
        // 为了防止被单例攻击，可以改造一下构造函数，在创建第二个实例时抛出异常
        Class<SingletonStaticNested> staticNestedClass = SingletonStaticNested.class;
        try {
            Constructor<SingletonStaticNested> declaredConstructor = staticNestedClass.getDeclaredConstructor(null);
            declaredConstructor.setAccessible(true);
            SingletonStaticNested instance2 = declaredConstructor.newInstance();  // output "instance created" two times
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * output
     * java.lang.reflect.InvocationTargetException
     instance created
     at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
     at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
     at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
     at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
     at com.jutils.singleton.SingletonStaticNested.main(SingletonStaticNested.java:43)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:498)
     at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
     Caused by: java.lang.RuntimeException: 不允许创建第二个实例
     at com.jutils.singleton.SingletonStaticNested.<init>(SingletonStaticNested.java:23)
     ... 10 more
     */
}
