package com.jutils.synchronize;

/**
 * ThreadName 0 is Running ... id: 1
 * Normal 0 is Running ... id: 1
 * ThreadName 1 is Running ... id: 1
 * Normal 1 is Running ... id: 2
 * ThreadName 2 is Running ... id: 1
 * Normal 2 is Running ... id: 3
 * ThreadName 3 is Running ... id: 1
 * Normal 4 is Running ... id: 5
 * ThreadName 4 is Running ... id: 1
 * Normal 3 is Running ... id: 4
 *
 * 结果是 Normal 的输出乱了，ThreadLocal 的输出都是 1
 */
public class ThreadLocalDemo {

	private static int normalVar;
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) {

		normalVar = 0;
		threadLocal.set(0);
		for (int i = 0; i < 5; i++) {
			new Thread(new ThreadLocalRunnable(), "ThreadName " + i).start();
			new Thread(new NormalRunable(), "Normal " + i).start();
		}
	}

	public static class ThreadLocalRunnable implements Runnable {

		@Override
		public void run() {
			if (threadLocal.get() == null) {
				threadLocal.set(0);
			}
			threadLocal.set(threadLocal.get() + 1);
			String currentName = Thread.currentThread().getName();
			System.out.println(currentName + " is Running ... id: " + threadLocal.get());
		}
	}

	public static class NormalRunable implements Runnable {

		@Override
		public void run() {
			normalVar++;
			String currentName = Thread.currentThread().getName();
			System.out.println(currentName + " is Running ... id: " + normalVar);
		}
	}
}
