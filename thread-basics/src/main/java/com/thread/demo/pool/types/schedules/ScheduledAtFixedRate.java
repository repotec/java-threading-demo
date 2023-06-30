package com.thread.demo.pool.types.schedules;

import com.thread.demo.pool.SleepingWorker;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedRate {
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

        //tasks to run every 3 seconds and delay for 2 seconds
        executor.scheduleAtFixedRate(new SleepingWorker(1),
                2,
                3L,
                TimeUnit.SECONDS);

        Thread.sleep(90000);
        executor.shutdownNow();
    }

}
