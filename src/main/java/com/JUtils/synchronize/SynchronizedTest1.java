package com.JUtils.synchronize;

/**
 * Created by mi on 17-10-11.
 */
public class SynchronizedTest1 {

    //当两个并发线程访问同一个对象 object 中的这个 synchronized(this) 同步代码块时，一个时间内只能有一个线程得到执行。
    // 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
    public static void main(String[] args) {
        ThreadOne t1 = new ThreadOne();
        Thread ta = new Thread(t1, "ta");
        Thread tb = new Thread(t1, "tb");
        ta.start();
        tb.start();
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
