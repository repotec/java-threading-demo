package com.thread.demo.atomic;

import com.thread.demo.ThreadRacing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class IntegerMultiThreadsIncremental {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("custom-group");
        List<Thread> threads = new ArrayList<>();

        for(int i =0; i < 1000; i++){
            Thread t = new Thread(group, new ProcessingThread());
            t.start();
            threads.add(t);
        }

        group.interrupt();

        threads.forEach((t)->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    static class ProcessingThread implements Runnable {
        private AtomicInteger count = new AtomicInteger();

        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                processSomething(i);
                count.incrementAndGet();
            }
        }

        public int getCount() {
            return this.count.get();
        }

        private void processSomething(int i) {
            // processing some job
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
