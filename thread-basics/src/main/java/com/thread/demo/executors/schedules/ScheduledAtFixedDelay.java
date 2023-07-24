package com.thread.demo.executors.schedules;

import com.thread.demo.pool.SleepingWorker;

import java.util.concurrent.*;

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
