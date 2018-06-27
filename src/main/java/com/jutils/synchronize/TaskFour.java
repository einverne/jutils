package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * synchronized block 和 synchronized method 区别
 * synchronized block 缩小了 synchronized scope，但是如果使用 synchronized(this) 也就和 synchronized method 是一样的
 *
 * 使用 synchronized method 时，是锁对象，也就意味当有线程访问该 synchronized method 时其他线程也无法访问类中其他 synchronized 方法
 */
public class TaskFour {
    public static void main(String[] args) {
        TaskFour four = new TaskFour();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                four.m1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                four.m2();
            }
        });
        t1.start();
        t2.start();
    }

    public synchronized void m1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("synchronized method " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void m2() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("synchronized block " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
