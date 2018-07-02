package com.jutils.notify;

import java.util.concurrent.TimeUnit;

public class ThreadListA extends Thread {

	private MyList list;

	public ThreadListA(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
			System.out.println("thread A add " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
