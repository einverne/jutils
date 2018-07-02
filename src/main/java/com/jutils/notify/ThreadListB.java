package com.jutils.notify;

import java.util.concurrent.TimeUnit;

public class ThreadListB extends Thread {

	private MyList list;

	public ThreadListB(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		super.run();
		try {
			// 线程通过轮询检测，浪费 CPU 资源
			while (true) {
				if (list.size() == 5) {
					System.out.println("Thread B exit ...");
					throw new InterruptedException();
				}
				TimeUnit.MILLISECONDS.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
