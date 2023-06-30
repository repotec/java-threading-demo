package com.thread.demo.pool;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public  class SleepingWorker implements Runnable{
    private final int counter;

    public SleepingWorker(int counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            System.out.println("[task:" + counter + "] [has been executed using thread:" + Thread.currentThread().getName() + "] [" + LocalTime.now() + "]");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
