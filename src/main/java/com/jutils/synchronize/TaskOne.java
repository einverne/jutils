package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 并发线程访问同一个对象
 */
public class TaskOne implements Runnable {
    //当两个并发线程访问 **同一个对象** object 中的这个 `synchronized(this)` 同步代码块时，一个时间内**只能有一个线程得到执行**。
    // 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
    public static void main(String[] args) {
        TaskOne t1 = new TaskOne();
        Thread ta = new Thread(t1, "ta");
        Thread tb = new Thread(t1, "tb");
        // 因为 TaskOne 中有 synchronized(this) 修饰，所以 tb 线程无法在 ta 线程访问时执行，被阻塞
        ta.start();
        tb.start();
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    输出
     tb synchronized loop 0
     tb synchronized loop 1
     tb synchronized loop 2
     tb synchronized loop 3
     tb synchronized loop 4
     ta synchronized loop 0
     ta synchronized loop 1
     ta synchronized loop 2
     ta synchronized loop 3
     ta synchronized loop 4
     */

}
