package com.thread.demo.synchronize.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountDownLatchSample2 {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        Thread cacheTask = new Thread(new Task("cache-task", 500, latch));
        Thread alterTask = new Thread(new Task("alert-task", 2000, latch));
        Thread validationTask = new Thread(new Task("validation-task", 500, latch));

        Logger.getLogger(CountDownLatchSample2.class.getName()).log(Level.INFO, "main-thread:"+ Thread.currentThread().getName());

        cacheTask.start(); //separate thread will initialize cache-task
        alterTask.start(); //another thread for alert-service initialization
        validationTask.start(); //another thread for validation-task initialization

        try{
            latch.await();  //main thread is waiting on CountDownLatch to finish
            System.out.println("All tasks are up, Application is starting now");
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }


    static class Task implements Runnable{
        private final String name;
        private final int timeToStart;
        private final CountDownLatch latch;

        public Task(String name, int timeToStart, CountDownLatch latch){
            this.name = name;
            this.timeToStart = timeToStart;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Logger.getLogger(Task.class.getName()).log(Level.INFO, Thread.currentThread().getName());
                Thread.sleep(timeToStart);
            } catch (InterruptedException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println( name + " is Up");
            latch.countDown(); //reduce count of CountDownLatch by 1 (decrement)
        }

    }
}
