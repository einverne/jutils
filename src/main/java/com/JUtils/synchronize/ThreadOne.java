package com.JUtils.synchronize;

/**
 * Created by mi on 17-10-11.
 */
public class ThreadOne implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }


}
