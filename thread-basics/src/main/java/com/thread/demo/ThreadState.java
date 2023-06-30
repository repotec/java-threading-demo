package com.thread.demo;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("[1] thread:" + Thread.currentThread().getName() + "|" + Thread.currentThread().getState());

        Thread thread1 = new Thread(()->{
            System.out.println("[2] thread:" + Thread.currentThread().getName() + "|" + Thread.currentThread().getState());
        });

        thread1.setName("thread-1");
        thread1.start();
        System.out.println("[3] thread:" + thread1.getName() + "|" + thread1.getState());
        thread1.join();
    }
}
