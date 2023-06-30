package com.thread.demo.exception;

import java.util.List;

public class CustomUncaughtExceptionHandler {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new CustomThread(), "thread-1") ;

        Thread.setDefaultUncaughtExceptionHandler((Thread thread, Throwable throwable) ->{
            System.err.println("here the exception1");
            throwable.printStackTrace();
        });

        //high priority for:
        //1- user thread DefaultUncaughtExceptionHandler
        //2- threadGroup
        //3- main thread DefaultUncaughtExceptionHandler
        t1.setDefaultUncaughtExceptionHandler((Thread thread, Throwable throwable) ->{
            System.err.println("here the exception2");
            throwable.printStackTrace();
        });

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
