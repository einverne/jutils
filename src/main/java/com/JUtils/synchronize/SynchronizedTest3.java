package com.JUtils.synchronize;

/**
 * Created by mi on 17-10-11.
 */
public class SynchronizedTest3 {
    /**
     * 尽管线程t1获得了对 Inner 的对象锁，但由于线程 t2 访问的是同一个Inner中的非同步部分。所以两个线程互不干扰。
     *
     * @param args
     */
    public static void main(String[] args) {
        final ThreadThree threadThree = new ThreadThree();
        final ThreadThree.Inner inner = threadThree.new Inner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadThree.m1(inner);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadThree.m2(inner);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
/** output
 * t1 method1 0
 t2 method1 0
 t1 method1 1
 t2 method1 1
 t1 method1 2
 t2 method1 2
 t1 method1 3
 t2 method1 3
 t1 method1 4
 t2 method1 4
 */
}
