package com.thread.demo.daemon;

public class DaemonThreadDemo extends Thread  {
    public static void main(String[] args) {
        CustomDaemonThread t1 = new CustomDaemonThread("t1");
        CustomDaemonThread t2 = new CustomDaemonThread("t2");
        CustomDaemonThread t3 = new CustomDaemonThread("t3");
        // Setting user thread t1 to Daemon 
        t1.setDaemon(true);
        // starting first 2 threads  
        t1.start();
        t2.start();
        // Setting user thread t3 to Daemon 
        t3.setDaemon(true);
        t3.start();
    }
}

class  CustomDaemonThread extends Thread {
    public CustomDaemonThread(String name) {
        super(name);
    }

    public void run() {
        if (Thread.currentThread().isDaemon())
            System.out.println(getName() + " is Daemon thread");
        else
            System.out.println(getName() + " is User thread");
    }
}