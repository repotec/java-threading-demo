package com.thread.demo.pool.exceptions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorRejectHandler {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                new CustomRejectedExecutionHandler()
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

    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("task has been rejected - total size as following:");
            System.err.println("total active tasks in pool:" + executor.getActiveCount());
            System.err.println("total tasks in blocking queues:" + executor.getQueue().size());
        }
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
