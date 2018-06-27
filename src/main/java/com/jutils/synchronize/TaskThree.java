package com.jutils.synchronize;

/**
 * 锁内部对象
 */
public class TaskThree {
    /**
     * 尽管线程t1获得了对 Inner 的对象锁，但由于线程 t2 访问的是同一个Inner中的非同步部分。所以两个线程互不干扰。
     *
     * @param args
     */
    public static void main(String[] args) {
        final TaskThree threadThree = new TaskThree();
        final TaskThree.Inner inner = threadThree.new Inner();

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

    /**
     * output
     * t1 method1 0
     * t2 method1 0
     * t1 method1 1
     * t2 method1 1
     * t1 method1 2
     * t2 method1 2
     * t1 method1 3
     * t2 method1 3
     * t1 method1 4
     * t2 method1 4
     */

    public void m1(Inner inner) {
        synchronized (inner) {
            // 对象锁
            inner.methodInner1();
        }
    }

    public void m2(Inner inner) {
        inner.methodInner2();
    }

    class Inner {
        private void methodInner1() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " methodInner1 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void methodInner2() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " methodInner2 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
