package com.jutils.async;

import java.util.concurrent.Callable;

public class DemoTask implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println("DemoTask running ...");
        Thread.sleep(2);
        return 100;
    }
}
