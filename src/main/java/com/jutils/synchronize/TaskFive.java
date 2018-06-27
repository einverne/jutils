package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Lock 实现同步
 */
public class TaskFive {

    private Lock lock = new ReentrantLock();
    private int var = 0;

    public static void main(String[] args) {
        TaskFive five = new TaskFive();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                five.m1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                five.m2();
            }
        });
        t1.start();
        t2.start();
    }

    public void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                var++;
                System.out.println("m1 " + var);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                var += 2;
                System.out.println("m1 " + var);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * output
     * m1 1
     * m1 2
     * m1 3
     * m1 4
     * m1 5
     * m1 7
     * m1 9
     * m1 11
     * m1 13
     * m1 15
     */
}
