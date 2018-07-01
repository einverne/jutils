package com.jutils.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举方式实现, 可以防止单例模式被“攻击”。
 */
public enum SingletonEnum {

    INSTANCE;
    private String value;

    SingletonEnum() {
        System.out.println("instance created");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        instance.setValue("hello");
        String value = instance.getValue();
        System.out.println(value);
        SingletonEnum instance1 = SingletonEnum.INSTANCE;

        Class<SingletonEnum> singletonEnumClass = SingletonEnum.class;
        try {
            Constructor<SingletonEnum> constructor = singletonEnumClass.getConstructor();
            constructor.setAccessible(true);
            SingletonEnum singletonEnum = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
