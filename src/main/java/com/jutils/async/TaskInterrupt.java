package com.jutils.async;

import java.util.concurrent.TimeUnit;

public class TaskInterrupt implements Runnable {
    private int innerVar = 0;

    public TaskInterrupt(int innerVar) {
        this.innerVar = innerVar;
    }

    public static void main(String[] args) {
        TaskInterrupt task1 = new TaskInterrupt(10);
        Thread thread = new Thread(task1);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("main thread " + task1.getInnerVar());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (thread.isInterrupted()) {
            System.out.println("current thread is interrupted");
        } else {
            System.out.println("current thread is not interrupted");
        }
        thread.interrupt();
        System.out.println("interrupt ? " + thread.isInterrupted());
        System.out.println("interrupt ? " + thread.isInterrupted());
    }

    @Override
    public void run() {
        System.out.println("start task 1 " + Thread.currentThread().getName());
        innerVar++;
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end task 1 " + Thread.currentThread().getName());
    }

    public int getInnerVar() {
        return innerVar;
    }

    public void setInnerVar(int innerVar) {
        this.innerVar = innerVar;
    }
}
