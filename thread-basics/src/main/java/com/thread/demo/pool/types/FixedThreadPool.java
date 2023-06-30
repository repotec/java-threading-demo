package com.thread.demo.pool.types;

import com.thread.demo.pool.SleepingWorker;

import java.util.concurrent.*;

public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ExecutorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            ExecutorService.execute(new SleepingWorker(i));
        }
    }
}
