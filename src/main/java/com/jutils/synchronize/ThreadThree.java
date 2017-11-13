package com.jutils.synchronize;

/**
 * Created by mi on 17-10-11.
 */
public class ThreadThree {

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
                System.out.println(Thread.currentThread().getName() + " method1 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void methodInner2() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " method1 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
