package com.thread.demo.management.daemon;

public class DaemonThread2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Worker(10), "T1");
        Thread t2 = new Thread(new Worker(3), "T2");
        t1.setDaemon(true);

        t1.start();
        t2.start();

        //to force the main thread to wait daemon thread
        t1.join();
    }

    static class Worker implements Runnable {
        private final int numberOfSeconds;

        public Worker(int numberOfSeconds){
            this.numberOfSeconds = numberOfSeconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfSeconds; i++) {
                try {
                    System.out.println("Sleeping for 1s, Thread:" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
