package com.thread.demo.pool.types;

import com.thread.demo.pool.SleepingWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool has only one room to queue and cache only one thread
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.execute(new SleepingWorker(i));
        }

        executor.shutdown();
    }
}
