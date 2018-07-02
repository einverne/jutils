package com.jutils.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在创建线程的时候，我们当然也能使用工厂模式来生产线程，ThreadFactory是用来实现创建线程的工厂接口，其实它只有一个方法Thread newThread(Runnable r)
 *
 * 使用ThreadFactory工厂能替代默认的new Thread，而且在自定义工厂里面，我们能创建自定义化的Thread，并且计数，或则限制创建Thread的数量，给每个Thread设置对应的好听的名字
 *
 * 在创建线程的同时，记录线程创建的个数，并记录每个线程创建的时间等信息。
 */
public class MyThreadFactory implements ThreadFactory {

	private String prefix;
	private AtomicInteger threadNum = new AtomicInteger(10000);
	private ThreadGroup group;
	private boolean isDaemon;

	/**
	 * prefix-Thread-10000
	 *
	 * @param prefix thread name prefix
	 */
	public MyThreadFactory(String prefix) {
		this(prefix, false);
	}

	public MyThreadFactory(String prefix, boolean isDaemon) {
		this.prefix = prefix.concat("-Thread-");
		SecurityManager s = System.getSecurityManager();
		group = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
		this.isDaemon = isDaemon;
	}

	public static void main(String[] args) {
		MyThreadFactory myFactory = new MyThreadFactory("MyFactory");
		Task task = new Task();
		Thread thread = null;
		for (int i = 0; i < 10; i++) {
			thread = myFactory.newThread(task);
			thread.start();
		}
	}

	@Override
	public Thread newThread(Runnable r) {
		int andIncrement = threadNum.getAndIncrement();
		Thread thread = new Thread(group, r, prefix + andIncrement, 0);
		thread.setDaemon(isDaemon);
		return thread;
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		System.out.println("Task running ... " + Thread.currentThread().getName());
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
