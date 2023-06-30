package com.thread.demo;

import java.util.ArrayList;
import java.util.List;

public class ThreadRacing {
    static int globalCounter = 0;

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("custom-group");
        List<Thread> threads = new ArrayList<>();

        for(int i =0; i < 1000; i++){
            Thread t = new Thread(group, new CustomThread());
            t.start();
            threads.add(t);
        }

        group.interrupt();

        //to ensure that the execution of all threads should be completed before return to the main thread
        //this allows us to see the full output
        threads.forEach((t)->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(globalCounter);
    }

    static class CustomThread implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

            }
            globalCounter++;
        }
    }
}


