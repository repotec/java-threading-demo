package com.thread.demo.executors.schedules;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool2 {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor threadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);

        Runnable task1 = new WorkerToSleep(1);
        Runnable task2 = new WorkerToSleep(2);

        threadPoolExecutor.schedule(task1, 0, TimeUnit.SECONDS);
        threadPoolExecutor.schedule(task2, 0, TimeUnit.SECONDS);

        threadPoolExecutor.shutdown();
    }

    private static class WorkerToSleep implements Runnable{
        private final int counter;

        public WorkerToSleep(int counter){
            this.counter = counter;
        }
        @Override
        public void run() {
            try {
                System.out.println("task :" + counter + " has been executed");
                Thread.sleep(2000); //do some logic
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
