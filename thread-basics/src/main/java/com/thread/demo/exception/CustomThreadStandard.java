package com.thread.demo.exception;

import java.util.List;

public class CustomThreadStandard {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new CustomThread(), "thread-1") ;
        t1.start();
        t1.join();
    }

    static class CustomThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("thread " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List test = null;
            test.add("test");
        }
    }
}
