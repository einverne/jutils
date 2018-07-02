package com.jutils.notify;

import java.util.concurrent.TimeUnit;

public class ThreadListC extends Thread {

	private MyList list;

	public ThreadListC(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		super.run();
		synchronized (list) {
			try {
				for (int i = 0; i < 10; i++) {
					list.add("" + i);
					System.out.println("Thread C add " + i);
					TimeUnit.MILLISECONDS.sleep(500);
					if (list.size() % 3 == 0) {
						System.out.println("Thread C wait");
						list.wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyList list = new MyList();
		new ThreadListC(list).start();
		new ThreadListD(list).start();
	}
}
