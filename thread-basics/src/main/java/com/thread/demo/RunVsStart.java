package com.thread.demo;

public class RunVsStart {
    public static void main(String[] args) {
        Thread r1 = new Thread(new MyCustom());
        Thread r2 = new Thread(new MyCustom());
        r1.start(); //start new thread
        r2.start(); //start new thread

        r1.run(); //will be executed in main thread
        r2.run(); //will be executed in main thread
    }

    static class MyCustom implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    System.out.println(i + "|thread:" + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("-------");
        }
    }
}

