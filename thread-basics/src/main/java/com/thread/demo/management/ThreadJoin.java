package com.thread.demo.management;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()-> sleep(0), "T1");
        Thread t2 = new Thread(()-> sleep(1), "T2");
        Thread t3 = new Thread(()-> sleep(2), "T3");

        // start and join t1.
        t1.start();
        t1.join();

        // start and join t2 after when thread t1 has died.
        t2.start();
        t2.join();

        // start and join t3 after when thread t2 has died.
        t3.start();
        t3.join();
    }

    static void sleep(int count) {
        try {
            Thread.sleep(1000 * (count == 0 ? 10 : 2));
            System.out.println("The current Thread: " + count + "|" + Thread.currentThread().getName());
        } catch(Exception ex) {
            System.out.println("Exception has" + " been caught" + ex);
        }
    }
}
