package com.jutils.synchronize;

/**
 * 并发线程在访问 synchronized(this) 同步代码块时，另外的线程仍然可以访问非同步代码块
 */
public class TaskTwo {

	// 当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的 非synchronized(this)同步代码块
	// 关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中**所有其它synchronized(this)同步代码块的访问将被阻塞**。
	// 当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个**object的对象锁**。
	// 其它线程对该 object 对象所有同步代码部分的访问都被暂时阻塞。
	public static void main(String[] args) {
		final TaskTwo two = new TaskTwo();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				two.methodCommon();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				two.methodSynced();
			}
		}, "t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				two.methodSyncedOther();
			}
		}, "t3");
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				two.method3();
			}
		}, "t4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
/*
t1 synced 0
t2 synced 0
t1 synced 1
t2 synced 1
t1 synced 2
t2 synced 2
t1 synced 3
t2 synced 3
t2 synced 4
t1 synced 4
t4 synced 0
t4 synced 1
t4 synced 2
t4 synced 3
t4 synced 4
t3 other synced 10
t3 other synced 9
t3 other synced 8
t3 other synced 7
t3 other synced 6
     */

	public void methodCommon() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " methodCommon " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void methodSynced() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " methodSynced " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void methodSyncedOther() {
		synchronized (this) {
			for (int i = 10; i > 5; i--) {
				System.out.println(Thread.currentThread().getName() + " methodSyncedOther " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void method3() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " synced " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
