package com.thread.demo.atomic;

import com.thread.demo.synchronize.countdownlatch.CountDownLatchSample2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IntegerMultiThreadsIncrementalProblem {
    public static void main(String[] args) throws InterruptedException {
        ProcessingThread pt = new ProcessingThread();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
    static class ProcessingThread implements Runnable {
        private int count;
        Logger logger = Logger.getLogger(String.valueOf(ProcessingThread.class));
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                processSomething(i);
                count++;
            }
        }

        public int getCount() {
            return this.count;
        }

        private void processSomething(int i) {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

