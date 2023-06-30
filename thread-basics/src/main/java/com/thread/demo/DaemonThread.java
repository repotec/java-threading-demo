package com.thread.demo;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new CustomThread(10), "thread-1");
        Thread t2 = new Thread(new CustomThread(2), "thread-2");
        t1.setDaemon(true);

        t1.start();
        t2.start();

        //to force the main thread to wait daemon thread
        //t1.join();
    }

    static class CustomThread implements Runnable {
        private final int number;

        public CustomThread(int number){
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < number; i++) {
                try {
                    System.out.println(i + 1 + "|thread " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
