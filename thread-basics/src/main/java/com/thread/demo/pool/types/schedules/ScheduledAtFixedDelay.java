package com.thread.demo.pool.types.schedules;

import com.thread.demo.pool.SleepingWorker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedDelay {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        //tasks to run every 3 seconds and delay for 2 seconds
        executor.scheduleWithFixedDelay(new SleepingWorker(1),
                1,
                3L,
                TimeUnit.SECONDS);

        Thread.sleep(100000);
        executor.shutdownNow();
    }

}
