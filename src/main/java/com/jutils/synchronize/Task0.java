package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 如果多个线程访问1个对象实例中的变量，就有可能出现 非线程安全 的情况
 */
public class Task0 {

	private int var = 0;

	public static void main(String[] args) {
		Task0 task0 = new Task0();
		ThreadA threadA = new ThreadA(task0);
		ThreadB threadB = new ThreadB(task0);
		threadA.start();
		threadB.start();
	}

	public void addOne() {
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

		private Task0 task0;

		public ThreadA(Task0 task0) {
			this.task0 = task0;
		}

		@Override
		public void run() {
			super.run();
			task0.addOne();
		}
	}

	public static class ThreadB extends Thread {

		private Task0 task0;

		public ThreadB(Task0 task0) {
			this.task0 = task0;
		}

		@Override
		public void run() {
			super.run();
			task0.addOne();
		}
	}

}
