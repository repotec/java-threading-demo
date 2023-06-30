package com.thread.demo.synchronize.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Locking {
    static final int MAX_DATA = 2;

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        // make a thread pool by creating an instance of the ExecutorService class
        ExecutorService pool = Executors.newFixedThreadPool(MAX_DATA);
        Runnable work1 = new Work(reentrantLock, "Job A");
        Runnable work2 = new Work(reentrantLock, "Job B");

        // execute each job of the pool using execute() method
        pool.execute(work1);
        pool.execute(work2);

        // use shutdown() method for shutting down the pool
        pool.shutdown();
    }

    static class Work implements Runnable {
        String threadName;
        ReentrantLock reentrantLock;

    public Work(ReentrantLock reentrantLock, String threadName) {
            this.reentrantLock = reentrantLock;
            this.threadName = threadName;
        }

        public void run() {
            reentrantLock.lock();

            try {
                System.out.println(Thread.currentThread().getName() + "  Time Taken 3 seconds.");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.printf("%s printed the document successfully.\n", Thread.currentThread().getName());
                reentrantLock.unlock();
            }
        }
    }
}

