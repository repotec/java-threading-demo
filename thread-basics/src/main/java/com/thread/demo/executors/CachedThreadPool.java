package com.thread.demo.executors;

import com.thread.demo.pool.SleepingWorker;

import java.util.concurrent.*;

/**
 * CachedThreadPool has only one room to queue and cache only one thread
 */
public class CachedThreadPool {
    public static void main(String[] args) {
       ExecutorService executor1 = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor1.execute(new SleepingWorker(i));
        }


        ThreadPoolExecutor executor =  (ThreadPoolExecutor) Executors.newCachedThreadPool();

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        System.out.println("<" + executor.getPoolSize() + "> pool size should be 3");
        System.out.println("<" + executor.getQueue().size() + "> pool size should be 0");
    }
}
