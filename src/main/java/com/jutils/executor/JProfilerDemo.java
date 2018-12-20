package com.jutils.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JProfilerDemo {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		final int loopCount = 2_000_000;
		final CountDownLatch countDownLatch = new CountDownLatch(loopCount);
		for (int i = 0; i < loopCount; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println("print " + countDownLatch.getCount());
					countDownLatch.countDown();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		countDownLatch.await();
	}
}
