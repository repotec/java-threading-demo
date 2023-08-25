package com.thread.demo.executors.schedules;

import com.thread.demo.pool.SleepingWorker;

import java.util.Calendar;
import java.util.concurrent.*;

public class ScheduledAtFixedDelay {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Runnable task1 = new WorkerToSleep(1);
        Runnable task2 = new WorkerToSleep(2);

        //tasks to run every 3 seconds and delay for 2 seconds
        executor.scheduleWithFixedDelay(task1, 3, 2L, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(task2, 4, 2L, TimeUnit.SECONDS);

        Thread.sleep(100000);

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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
