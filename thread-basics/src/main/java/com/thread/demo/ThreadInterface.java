package com.thread.demo;

public class ThreadInterface {


    public static void main(String[] args) {
        Custom threadInterface = new Custom();
        Thread r1 = new Thread(threadInterface);
        r1.start();
    }
}

class customThread implements Runnable{
    @Override
    public void run() {
        System.out.println("hello world Interface!");
    }
}