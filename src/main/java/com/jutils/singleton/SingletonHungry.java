package com.jutils.singleton;

/**
 * 饿汉，类加载较慢
 */
public class SingletonHungry {
    private SingletonHungry() {
        System.out.println("instance created");
    }

    private static SingletonHungry instance = new SingletonHungry();

    public static SingletonHungry getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SingletonHungry instance = SingletonHungry.getInstance();
        SingletonHungry instance1 = SingletonHungry.getInstance();
    }
}
