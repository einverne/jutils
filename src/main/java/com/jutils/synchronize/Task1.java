package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 当变量为方法内部变量时，不存在 线程安全问题
 */
public class Task1 {

	public static void main(String[] args) {
		Task1 task01 = new Task1();
		ThreadA threadA = new ThreadA(task01);
		ThreadB threadB = new ThreadB(task01);
		threadA.start();
		threadB.start();
	}

	public void addOne() {
		int var = 0;            // 变量为方法内部变量
		while (true) {
			try {
				var += 1;
				System.out.println("addOne " + var);
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class ThreadA extends Thread {

		private Task1 task1;

		public ThreadA(Task1 task1) {
			this.task1 = task1;
		}

		@Override
		public void run() {
			super.run();
			task1.addOne();
		}
	}

	public static class ThreadB extends Thread {

		private Task1 task1;

		public ThreadB(Task1 task1) {
			this.task1 = task1;
		}

		@Override
		public void run() {
			super.run();
			task1.addOne();
		}
	}
}
