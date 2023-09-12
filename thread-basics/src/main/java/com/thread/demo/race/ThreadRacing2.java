package com.thread.demo.race;

import java.util.ArrayList;
import java.util.List;

public class ThreadRacing2 {
    static int globalCounter = 0;

    public static void main(String[] args) throws InterruptedException {
        WorkerRaceCondition workerRaceCondition = new WorkerRaceCondition();

        Thread t1 = new Thread(workerRaceCondition);
        t1.start();

        Thread t2 = new Thread(workerRaceCondition);
        t2.start();

        //to ensure that the execution of all threads should be completed before return to the main thread
        //this allows us to see the full output
        t1.join();
        t2.join();

        System.out.println(globalCounter);
    }

    static class WorkerRaceCondition implements Runnable{
        @Override
        public void run() {
            System.out.println("thread:" + Thread.currentThread().getName());
            for(int i =0; i < 5; i++) {
                try {
                    Thread.sleep(i * 100);
                    globalCounter++;
                } catch (InterruptedException e) {

                }
            }
        }
    }
}


