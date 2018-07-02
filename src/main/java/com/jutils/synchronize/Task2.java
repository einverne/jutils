package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁，再次获取自己内部锁，可重入锁也支持在父子类继承中。比如说下面 addTwo 同步方法，
 * 可以同时重入父类的 add 方法
 */
public class Task2 {

	protected int var = 0;

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				SubTask2 subTask2 = new SubTask2();
				subTask2.addTwo();
			}
		}).start();
	}

	synchronized public void add() {
		try {
			var++;
			System.out.println("var + 1 value " + var);
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class SubTask2 extends Task2 {

		synchronized public void addTwo() {
			while (var < 10) {
				try {
					var += 2;
					System.out.println("var + 2 value " + var);
					TimeUnit.MILLISECONDS.sleep(500);
					add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
