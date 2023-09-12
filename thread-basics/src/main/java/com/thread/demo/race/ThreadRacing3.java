package com.thread.demo.race;

public class ThreadRacing3 {
    public static void main(String[] args) throws InterruptedException {
        Sequence sequence = new Sequence();

        Thread t1 = new Thread(new Worker(sequence));
        t1.start();

        t1.join();

        Thread t2 = new Thread(new Worker(sequence));
        t2.start();
    }

    static class Worker implements Runnable{
        Sequence sequence = null;

        public Worker(Sequence sequence){
            this.sequence = sequence;
        }

        @Override
        public void run() {

            for(int i =0; i < 100; i++) {
                try {
                    System.out.println("thread:" + Thread.currentThread().getName() + " get:" + sequence.getNext());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Sequence{
        private int value;

        public int getNext(){
            value++;
            return value;
        }

        //the solution is to synchronize this block

        //public synchronized int getNext(){
        //    value++;
        //    return value;
        //}

        //or

        //public int getNext(){
        //    synchronized (this){
        //        value++;
        //        return value;
        //    }
        //}
    }
}


