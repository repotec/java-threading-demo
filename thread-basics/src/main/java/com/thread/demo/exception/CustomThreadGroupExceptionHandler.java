package com.thread.demo.exception;

import java.util.List;

public class CustomThreadGroupExceptionHandler {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new CustomThreadGroup("group-1"), new CustomThread(), "thread-1") ;
        t1.start();
        t1.join();
    }

    static class CustomThreadGroup extends ThreadGroup{
        public CustomThreadGroup(String name) {
            super(name);
        }

        public CustomThreadGroup(ThreadGroup parent, String name) {
            super(parent, name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("custom exception handler|error message is:" + e.getMessage());
        }
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
