package com.JUtils.synchronize;

/**
 * Created by mi on 17-10-11.
 */
public class SynchronizedTest2 {
    // 当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的 非synchronized(this)同步代码块
    // 关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
    // 当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。
    // 其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
    public static void main(String[] args) {
        final ThreadTwo two = new ThreadTwo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                two.methodCommon();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                two.methodSynced();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                two.methodSyncedOther();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
/*
t1 synced 0
t2 synced 0
t1 synced 1
t2 synced 1
t1 synced 2
t2 synced 2
t1 synced 3
t2 synced 3
t1 synced 4
t2 synced 4
t3 other synced 10
t3 other synced 9
t3 other synced 8
t3 other synced 7
t3 other synced 6
     */
}
