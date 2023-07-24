package com.thread.demo.executors.schedules;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        executor.schedule(new WorkerToSleep(1), 0, TimeUnit.SECONDS);
        executor.schedule(new WorkerToSleep(2), 0, TimeUnit.SECONDS);
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
