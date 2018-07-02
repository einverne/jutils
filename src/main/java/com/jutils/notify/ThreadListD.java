package com.jutils.notify;

import java.util.concurrent.TimeUnit;

public class ThreadListD extends Thread {

	private MyList list;

	public ThreadListD(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		super.run();
		synchronized (list) {
			try {
				while (true) {
					TimeUnit.MILLISECONDS.sleep(500);
					if (list.size() % 3 == 0) {
						System.out.println("Thread D notify");
						list.notify();   // notify 之后不会立即释放对象锁，需要执行完这个 synchronized block
						break;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
