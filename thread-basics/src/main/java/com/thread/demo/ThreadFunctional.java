package com.thread.demo;

public class ThreadFunctional {
    public static void main(String[] args) {

        Thread r1 = new Thread(()->{
            System.out.println("hello world functional");
        });

        r1.start();
    }
}
