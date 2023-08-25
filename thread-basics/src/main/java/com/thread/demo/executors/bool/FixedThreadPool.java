package com.thread.demo.executors.bool;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Worker(i));
        }

        executor.shutdown();
    }

    static class Worker implements Runnable{
        private final int counter;

        public Worker(int counter){
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                System.out.println("[task:" + counter + "] [has been executed using thread:" + Thread.currentThread().getName() + "] [" + LocalTime.now() + "]");
                Thread.sleep(1000); //some logic
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
