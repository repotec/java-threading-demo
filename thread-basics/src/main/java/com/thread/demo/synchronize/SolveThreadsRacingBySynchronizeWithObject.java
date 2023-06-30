package com.thread.demo.synchronize;

import java.util.ArrayList;
import java.util.List;

public class SolveThreadsRacingBySynchronizeWithObject {
    private static int globalCounter = 0;
    private static Object object = new Object();

    public static void main(String[] args) {
        ThreadGroup g = new ThreadGroup("group-1");

        List<Thread> threads = new ArrayList<>();

        for(int i =0; i < 1000; i++){
            Thread t = new Thread(g, new CustomThread());
            t.start();
            threads.add(t);
        }

        g.interrupt();
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
            } catch (InterruptedException e) {}

            synchronized (object) {
                globalCounter++;
            }
        }
    }
}
