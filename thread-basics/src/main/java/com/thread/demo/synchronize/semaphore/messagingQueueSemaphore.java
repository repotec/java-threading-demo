package com.thread.demo.synchronize.semaphore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class messagingQueueSemaphore {
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable{
        private final Queue<String> queue;

        Producer(Queue queue){this.queue = queue;}

        @Override
        public void run() {
            while (true){
                try {
                    semaphore.acquire();

                    Thread.sleep(1000);

                    System.out.println("producing message ... [message_" + new Random().nextInt() + "-" + queue.size() + "]");
                    queue.add("message_" + new Random().nextInt() + "-" + queue.size());

                    if (queue.size() == 10) {
                        System.out.println("producer is now waiting...");
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private final Queue<String> queue;

        Consumer(Queue queue){this.queue = queue;}

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(600);

                    if (queue.isEmpty()) {
                        System.out.println("consumer is waiting for messages...");
                        semaphore.release();
                    }else {
                        semaphore.acquire();
                        String data = queue.remove();
                        System.out.println("consuming data:" + data);
                        semaphore.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}