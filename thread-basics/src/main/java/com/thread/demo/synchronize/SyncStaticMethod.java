package com.thread.demo.synchronize;

public class SyncStaticMethod {
    public static void main(String[] args) {

    }

    public static void doSomething(){
        synchronized (SyncStaticMethod.class){

        }
    }
}
