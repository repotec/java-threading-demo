package com.thread.demo.synchronize.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockForArraySum {
    private static int sum = 0;
    private static int[] array = new int[10];
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        //adding some values
        for (int i = 0; i < 10; i++) {
            array[i] = 10;
        }

        List<Thread> threads = new ArrayList<>();

        int threadSlides = array.length / 2;

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(new SumWorker((i * threadSlides), (i + 1) * threadSlides));
            t.start();
            threads.add(t);
        }

        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(sum);
    }

    static class SumWorker implements Runnable{
        private final int left;
        private final int right;

        public SumWorker(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            for(int i = left; i < right; i++){
                try {
                    lock.lock();
                    sum = sum + array[i];
                    System.out.println(Thread.currentThread().getName() + " sum is " + sum);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
