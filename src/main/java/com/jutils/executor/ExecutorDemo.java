package com.jutils.executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

	public static void main(String[] args) {
		Server server = new Server();
		for (int i = 0; i < 50; i++) {
			Task task = new Task("Task " + i);
			server.exeTask(task);
		}

		List<Future> resultList = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			CallableTask task = new CallableTask(i);
			Future submit = server.submit(task);
			resultList.add(submit);
		}

		while (true) {
			System.out.println("Check every 500ms");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (server.getActiveCnt() <= 0) {
				break;
			}
		}
		// 执行器需要显示的结束它，否则程序不会结束。执行器没有任何任务可以执行，那么会一直等待。
		server.stop();
		for (Future future : resultList) {
			try {
				System.out.println("result: " + future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public static class CallableTask implements Callable<Integer> {

		private int cnt;

		public CallableTask(int cnt) {
			this.cnt = cnt;
		}

		@Override
		public Integer call() throws Exception {
			int total = 0;
			for (int i = 0; i < cnt; i++) {
				total += i;
				TimeUnit.MILLISECONDS.sleep(10);
			}
			System.out.println("caculating ... " + cnt);
			return total;
		}
	}

	public static class Task implements Runnable {

		private Date initDate;
		private String name;

		public Task(String name) {
			this.initDate = new Date();
			this.name = name;
		}

		public Date getInitDate() {
			return initDate;
		}

		public void setInitDate(Date initDate) {
			this.initDate = initDate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.printf("%s: Task %s, create at %s\n", Thread.currentThread().getName(), name,
				initDate);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s: Task %s, end at %s\n", Thread.currentThread().getName(), name,
				initDate);
		}
	}

	public static class Server {

		private ThreadPoolExecutor executor;

		public Server() {
			this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		}

		public void exeTask(Task task) {
			executor.execute(task);
		}

		public Future submit(Callable c) {
			Future submit = executor.submit(c);
			return submit;
		}

		public void stop() {
			executor.shutdown();
		}

		public int getActiveCnt() {
			return executor.getActiveCount();
		}

		public long getCompleteCnt() {
			long cnt = executor.getCompletedTaskCount();
			System.out.println("getCnt " + cnt);
			return cnt;
		}
	}
}
