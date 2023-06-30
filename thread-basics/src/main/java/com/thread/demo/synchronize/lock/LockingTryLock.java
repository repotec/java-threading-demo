package com.thread.demo.synchronize.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockingTryLock {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        Runnable work1 = new Worker("Worker 1");
        Runnable work2 = new Worker("Worker 2");

        pool.execute(work1);
        pool.execute(work2);

        pool.shutdown();
    }

    static class Worker implements Runnable {
        String workerName;

        public Worker(String workerName) {
            this.workerName = workerName;
        }

        public void run() {
            try {
                boolean acquired = lock.tryLock(4, TimeUnit.SECONDS);
                System.out.println(workerName + " try to lock - is " + acquired + " - Hold Count is:" + lock.getHoldCount());

                if (acquired) {
                    try {
                        System.out.println(workerName + " Time Taken 3 seconds.");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(workerName + " printed the document successfully.");
                        lock.unlock();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
