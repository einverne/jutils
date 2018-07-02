package com.jutils.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 关键字 volatile 主要作用是让变量在多个线程间可见。
 *
 * volatile vs synchronized
 *
 * - volatile 关键字是程序同步轻量级实现，性能稍好，volatile 只能修饰变量，而 synchronized 可以修饰方法，代码块
 * - 多线程访问 volatile 不会阻塞， synchronized 会阻塞
 * - volatile 能保证数据可见性，不能保证原子性；synchronized 可以保证原子性，也能间接保证可见性，synchronized 会将私有内存和公共内存的数据同步。
 */
public class VolatileDemo implements Runnable {

	private volatile boolean isContinuePrint = true;
	private int var = 0;

	public VolatileDemo(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}

	public static void main(String[] args) {
		VolatileDemo demo = new VolatileDemo(true);
		Thread thread = new Thread(demo);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		demo.setContinuePrint(false);
	}

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean continuePrint) {
		isContinuePrint = continuePrint;
	}

	public void print() {
		while (isContinuePrint) {
			var++;
			System.out.println(
				"Thread " + Thread.currentThread().getName() + " is printing var is " + var);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		print();
	}
}
