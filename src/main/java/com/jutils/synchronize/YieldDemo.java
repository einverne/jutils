package com.jutils.synchronize;

/**
 * 暂停当前正在执行的线程对象，并执行其他线程。在多线程的情况下，由CPU决定执行哪一个线程， 而yield()方法就是暂停当前的线程，让给其他线程（包括它自己）执行，具体由谁执行由CPU决定。
 * 线程总是存在优先级，优先级范围在1~10之间。JVM线程调度程序是基于优先级的抢先调度机制。 在大多数情况下，当前运行的线程优先级将大于或等于线程池中任何线程的优先级。但这仅仅是大多数情况。
 */
public class YieldDemo {


	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new ARunnable(), "A");
		Thread t2 = new Thread(new BRunnable(), "B");
		t1.start();
		t2.start();
		// A B 线程 yield 并不能保证先后

		t1.join();
		// main 输出一定在 A 线程 之后
		System.out.println("main ");
	}
}

class ARunnable implements Runnable {

	private volatile boolean isRunning = true;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			Thread.yield();
		}
	}
}


class BRunnable implements Runnable {

	private volatile boolean isRunning = true;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
}
