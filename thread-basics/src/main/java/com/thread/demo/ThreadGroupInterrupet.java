package com.thread.demo;

public class ThreadGroupInterrupet {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup1 = new ThreadGroup("custom-group-1");
        threadGroup1.setMaxPriority(Thread.NORM_PRIORITY);

        Thread t1 = new Thread(threadGroup1, new MyThread(), "thread1");
        Thread t2 = new Thread(threadGroup1, new MyThread(), "thread2");
        Thread t3 = new Thread(threadGroup1, new MyThread(), "thread3");

        Thread.sleep(2000);
        t1.start();
        t2.start();
        t3.start();

        threadGroup1.interrupt();
    }

    public static void printThreadOptions(Thread t){
        System.out.println(t.getName() + "|" + t.getThreadGroup().getName() + "|" + t.getPriority());
    }

    static class MyThread implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    printThreadOptions(Thread.currentThread());
                }
            }
        }
    }
}


