package com.thread.demo.executors.schedules;

import com.thread.demo.pool.SleepingWorker;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedRate {
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

        Runnable task1 = new WorkerToSleep(10);
        Runnable task2 = new WorkerToSleep(23);

        // Scheduling the first task which will execute after 2 seconds and then repeats periodically with
        // a period of 8 seconds
        executor.scheduleAtFixedRate(task1, 2, 8L, TimeUnit.SECONDS);

        // Scheduling the second task which will execute after 5 seconds and then there will be a delay of
        // 5 seconds between the completion of one execution and the commencement of the next execution
        executor.scheduleAtFixedRate(task2, 5, 5L, TimeUnit.SECONDS);

        Thread.sleep(20000);

        executor.shutdownNow();
    }


    private static class WorkerToSleep implements Runnable{
        private final int counter;

        public WorkerToSleep(int counter){
            this.counter = counter;
        }
        @Override
        public void run() {
            try {
                System.out.println("task :" + counter + " has been executed at " + "Current time:" + Calendar.getInstance().get(Calendar.SECOND));
                Thread.sleep(2000); //do some works
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
