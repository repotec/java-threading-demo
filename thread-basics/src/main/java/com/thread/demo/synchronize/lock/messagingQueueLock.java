package com.thread.demo.synchronize.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class messagingQueueLock {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {
        final Queue<String> queue;

        public Producer(Queue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    System.out.println("producer will lock");
                    lock.lock();

                    if (queue.size() == 10) {
                        condition.await(); //suspend
                    }

                    TimeUnit.MILLISECONDS.sleep(1000);
                    String message = "[message_" + new Random().nextInt() + "-" + queue.size() + "]";
                    System.out.println("producing message ... " + message);
                    queue.add(message);

                    if(queue.size() == 1) {
                        condition.signal(); // release condition - signal for consumer
                    }
                } catch (InterruptedException e) {
                   e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        final Queue<String> queue;

        public Consumer(Queue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("consumer will lock");
                    lock.lock();

                    if (queue.isEmpty()){
                        System.out.println("consumer suspend condition ....");
                        condition.await();
                    }

                    TimeUnit.MILLISECONDS.sleep(300);
                    String message = queue.remove();
                    System.out.println("consuming message ... " + message);

                    if(queue.size() == 9) {
                        condition.signal();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

}