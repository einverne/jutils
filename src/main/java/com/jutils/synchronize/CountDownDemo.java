package com.jutils.synchronize;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch：一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行。
 *
 * 年末公司组织团建，要求每一位员工周六上午8点到公司门口集合，统一乘坐公司所租大巴前往目的地。 在这个案例中，公司作为主线程，员工作为子线程。
 */
public class CountDownDemo {

	public static void main(String[] args) {
		int cnt = 5;
		CountDownLatch latch = new CountDownLatch(cnt);
		ExecutorService pool = Executors.newFixedThreadPool(cnt);

		for (int i = 0; i < cnt; i++) {
			pool.execute(new Employee(latch, i));
		}

		try {
			latch.await();
			System.out.println("All employee arrived");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}
}


class Employee implements Runnable {

	private CountDownLatch latch;
	private int employeeIndex;

	public Employee(CountDownLatch latch, int employeeIndex) {
		this.latch = latch;
		this.employeeIndex = employeeIndex;
	}

	@Override
	public void run() {
		try {
			System.out.println("Employee " + employeeIndex + ", 正在前往公司大门口集合...");
			int i = new Random().nextInt(10);
			TimeUnit.SECONDS.sleep(i);
			System.out.println("Employee " + employeeIndex + " 已到达. 路上花费 " + i + " 秒");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Employee " + employeeIndex + " 做自己的事情。");
		}
	}
}
