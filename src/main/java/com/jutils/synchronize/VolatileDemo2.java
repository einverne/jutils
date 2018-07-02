package com.jutils.synchronize;

/**
 * volatile 能够保证可见性，但是不能保证同步
 */
public class VolatileDemo2 implements Runnable {

	private volatile static int var;

	public static void main(String[] args) {

		Thread[] threads = new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new VolatileDemo2());
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	synchronized public static void add() {
		for (int i = 0; i < 100; i++) {
			var++;
		}
		System.out.println("var value " + var);
	}

	@Override
	public void run() {
		add();
	}
}
