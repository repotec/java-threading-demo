package com.thread.demo.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            counter.set(1);
        });
        executor.submit(() -> {
            counter.compareAndSet(1, 2);
        });
    }
}
