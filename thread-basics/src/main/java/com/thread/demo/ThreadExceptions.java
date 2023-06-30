package com.thread.demo;

public class ThreadExceptions {
    public static void main(String[] args) {
        Thread r1 = new Thread(new Custom());
        r1.start();
        r1.start();
    }
}

class Custom implements Runnable {
    @Override
    public void run() {
        System.out.println("hello world Interface!");
    }
}