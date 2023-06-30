package com.thread.demo;

public class ThreadClass extends Thread {
    public void run(){
        System.out.println("hello world Class!");
    }

    public static void main(String[] args) {
        ThreadClass r1 = new ThreadClass();
        r1.start();
    }
}
