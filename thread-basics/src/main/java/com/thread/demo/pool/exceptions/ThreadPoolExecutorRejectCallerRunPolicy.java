package com.thread.demo.pool.exceptions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorRejectCallerRunPolicy {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy() // means that thread which called submit will call the task that has been rejected
        );

        threadPoolExecutor.submit(new Sleeping(1));
        threadPoolExecutor.submit(new Sleeping(2));

        System.out.println("pool size:" + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new Sleeping(3));
        threadPoolExecutor.submit(new Sleeping(4));

        threadPoolExecutor.submit(new Sleeping(5));

        System.out.println("pool size:" + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new Sleeping(6));

        threadPoolExecutor.shutdown();
    }

    private static class Sleeping implements Runnable{
        private final int counter;

        public Sleeping(int counter){
            this.counter = counter;
        }
        @Override
        public void run() {
            try {
                System.out.println("task :" + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
