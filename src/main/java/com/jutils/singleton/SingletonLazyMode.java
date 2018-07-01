package com.jutils.singleton;

/**
 * 懒汉式，线程安全
 */
public class SingletonLazyMode {
    private static SingletonLazyMode instance;

    private SingletonLazyMode() {
        System.out.println("instance created");
    }

    /**
     * synchronized 同步锁，每次只能有一个线程调用，不实用
     */
    public synchronized static SingletonLazyMode getInstance() {
        if (instance == null) {
            instance = new SingletonLazyMode();
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonLazyMode instance = SingletonLazyMode.getInstance();
        SingletonLazyMode instance1 = SingletonLazyMode.getInstance();
    }
}
