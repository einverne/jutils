package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 当同步方法出现异常时，锁会自动释放
 *
 * 比如下面 A 线程中访问 add 同步方法出现异常，之后锁自动释放， 然后 B 线程可以进入
 *
 * 另外： 同步不可以继承
 */
public class Task3 {

	private int var = 0;

	public static void main(String[] args) {
		Task3 task3 = new Task3();
		new Thread(new Runnable() {
			@Override
			public void run() {
				task3.add();
			}
		}, "A").start();
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				task3.add();
			}
		}, "B").start();
	}

	synchronized public void add() {
		if (Thread.currentThread().getName().equals("A")) {
			var = var / 0; // create Exception
		} else {
			try {
				var++;
				System.out.println("var value " + var);
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
