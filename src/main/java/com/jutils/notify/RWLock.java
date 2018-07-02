package com.jutils.notify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写操作是互斥的，写读也是互斥，只要出现写操作就是互斥的。
 */
public class RWLock {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private int value;

	public RWLock() {
		this.value = 0;
	}

	/**
	 * 读锁不阻塞
	 */
	public static void main(String[] args) {
		RWLock rwLock = new RWLock();
		ThreadR1 r1 = new ThreadR1(rwLock);
		ThreadR2 r2 = new ThreadR2(rwLock);
		ThreadW1 w1 = new ThreadW1(rwLock);
		r1.start();
		r2.start();
		w1.start();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void read() {
		try {
			lock.readLock().lock();
			System.out.println("lock read value: " + value + " " + System.currentTimeMillis());
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}

	private void write(int var) {
		try {
			lock.writeLock().lock();
			value = var;
			System.out.println("lock write value: " + value + " " + System.currentTimeMillis());
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}

	public static class ThreadR1 extends Thread {

		private RWLock rwLock;

		public ThreadR1(RWLock rwLock) {
			this.rwLock = rwLock;
		}

		@Override
		public void run() {
			super.run();
			rwLock.read();
		}
	}

	public static class ThreadR2 extends Thread {

		private RWLock rwLock;

		public ThreadR2(RWLock rwLock) {
			this.rwLock = rwLock;
		}

		@Override
		public void run() {
			super.run();
			rwLock.read();
		}
	}

	public static class ThreadW1 extends Thread {

		private RWLock rwLock;

		public ThreadW1(RWLock rwLock) {
			this.rwLock = rwLock;
		}

		@Override
		public void run() {
			super.run();
			rwLock.write(1);
		}
	}
}
