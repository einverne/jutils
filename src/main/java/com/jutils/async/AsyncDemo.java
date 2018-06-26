package com.jutils.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        DemoTask demoTask = new DemoTask();

        Future<Integer> result = executorService.submit(demoTask);
        executorService.shutdown();

//        FutureTask<Integer> futureTask = new FutureTask<>(demoTask);
//        Thread thread = new Thread(futureTask);
//        thread.start();

        try {
            Thread.sleep(3);
            System.out.println("main thread running");
            System.out.println("result " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
