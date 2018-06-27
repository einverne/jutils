package com.jutils.async;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClockDemo extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("current time " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ClockDemo clockDemo = new ClockDemo();
        clockDemo.start();
    }
}
