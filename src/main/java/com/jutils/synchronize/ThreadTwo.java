package com.jutils.synchronize;

/**
 * Created by mi on 17-10-11.
 */
public class ThreadTwo {
    public void methodCommon() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synced " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void methodSynced() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synced " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void methodSyncedOther() {
        synchronized (this) {
            for (int i = 10; i > 5; i --) {
                System.out.println(Thread.currentThread().getName() + " other synced " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
