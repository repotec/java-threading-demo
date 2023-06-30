package com.thread.demo.synchronize;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MessagingQueueWaitNotify {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

        //we don't need to use join method for threads in main method. as our threads has infinite loops (long-running)
    }

    static class Producer implements Runnable{
        private final Queue<String> queue;

        Producer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    produceData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void produceData() throws InterruptedException {
            //synchronization block to let only one thread can access the queue object inside the critical block
            synchronized(queue) {
                if (queue.size() == 10) {
                    System.out.println("producer is now waiting...");
                    queue.wait(); //wait indefinitely until consumer thread invokes notify()
                }

                Thread.sleep(1000);

                System.out.println("producing message ... [message_"  + new Random().nextInt() + "-" + queue.size() + "]");
                queue.add("message_" + new Random().nextInt() + "-" + queue.size());

                if(queue.size() == 1) {
                    queue.notify(); //wake up consumer thread  [only consumer thread that's waiting for queue object]
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private final Queue<String> queue;

        Consumer(Queue<String> queue) {
            this.queue = queue;
        }

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
            //synchronization block to let only one thread can access the queue object inside the critical block
            synchronized(queue) {
                if (queue.isEmpty()) {
                    System.out.println("consumer is waiting for messages...");
                    queue.wait(); //wait indefinitely until producer thread invokes notify()
                }

                Thread.sleep(500);

                String element = queue.remove();
                System.out.println("consuming message:" + element);

                if(queue.size() == 9) {
                    queue.notify(); //wake up producer thread  [only producer thread that's waiting for queue object]
                }
            }
        }
    }
}
