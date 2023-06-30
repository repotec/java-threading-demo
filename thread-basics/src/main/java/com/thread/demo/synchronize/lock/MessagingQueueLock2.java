package com.thread.demo.synchronize.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MessagingQueueLock2 {
    private static Queue<String> queue = new LinkedList<>();
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
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
                    ProduceData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void ProduceData() throws InterruptedException {
            lock.lock();

            if (queue.size() == 10) {
                System.out.println("producer is now waiting...");
                condition.await();
            }

            Thread.sleep(1000);

            System.out.println("producing message ... [message_" + new Random().nextInt() + "-" + queue.size() + "]");
            queue.add("message_" + new Random().nextInt() + "-" + queue.size());
            if(queue.size() == 1) {
                condition.signal();
            }

            lock.unlock();
        }
    }

    static class Consumer implements Runnable{
        private final Queue<String> queue;

        Consumer(Queue queue){this.queue = queue;}

        @Override
        public void run() {
            while (true) {
                try {
                    ConsumeData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void ConsumeData() throws InterruptedException {
            lock.lock();

            if (queue.isEmpty()) {
                System.out.println("consumer is waiting for messages...");
                condition.await();
            }

            Thread.sleep(600);

            String data = queue.remove();
            System.out.println("consuming data:" + data);

            if(queue.size() == 9) {
                condition.signal();
            }

            lock.unlock();
        }
    }

}