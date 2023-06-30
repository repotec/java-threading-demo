package com.thread.demo.synchronize;

public class SyncMethod {
    public static void main(String[] args) {
            Object obj = new Object();
            synchronized (obj){
                System.out.println("");
            }
    }

    public synchronized void doSomethingSynchronizedSignature(){
        System.out.println("");
    }

    public  void doSomethingSynchronized(){
        synchronized(this) {
            System.out.println("");
        }
    }
}
