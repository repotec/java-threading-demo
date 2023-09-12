package com.thread.demo.executors.bool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DemoCustomThreadPoolExecutor{
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue =
                new LinkedBlockingQueue<Runnable>();

        CustomThreadPoolExecutor executor =
                new CustomThreadPoolExecutor(10,
                                         20,
                                            5,
                                            TimeUnit.SECONDS,
                                            blockingQueue,
                                            new ThreadPoolExecutor.AbortPolicy());

        // Let start all core threads initially
        executor.prestartAllCoreThreads();

        for (int i = 1; i <= 100; i++) {
            blockingQueue.offer(new WorkerThread("Task " + i));
        }

        executor.shutdown();
        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    public static class CustomThreadPoolExecutor extends ThreadPoolExecutor{

        public  CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                         long keepAliveTime, TimeUnit unit,
                                         BlockingQueue<Runnable> workQueue, AbortPolicy abortPolicy) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, abortPolicy);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            System.out.println("Perform beforeExecute");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t != null) {
                System.out.println("Perform exception handler logic");
            }
            System.out.println("Perform afterExecute");
        }
    }


    public static class WorkerThread implements Runnable {
        private String name = null;

        public WorkerThread(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Executing : " + name);
        }
    }
}
