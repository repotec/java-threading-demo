package com.thread.demo;

import java.util.concurrent.*;

public class CallableVsRunnable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CallableTask callableTask = new CallableTask(-1);
        Future<Integer> future = executorService.submit(callableTask);
        int result = future.get();
        System.out.println(result);

        System.out.println("----------------------------");

        executorService = Executors.newSingleThreadExecutor();
        RunnableTask runnableTask = new RunnableTask(-1);
        executorService.submit(runnableTask);
        executorService.shutdown();
    }

    //It returns a result and therefore, can throw an exception.
    static class CallableTask implements Callable<Integer> {
        int number;
        public CallableTask(int number){
            this.number = number;
        }

        public Integer call() throws RuntimeException {
            if(number < 0) {
                throw new RuntimeException("Number should be positive");
            }

            int fact = 1;

            for(int count = number; count > 1; count--) {
                fact = fact * count;
            }

            return fact;
        }
    }

    //It does not return any result and therefore, cannot throw a checked exception.
    static class RunnableTask implements Runnable {
        int number;
        public RunnableTask(int number){
            this.number = number;
        }
        @Override
        public void run() {
            if(number < 0) {
                throw new RuntimeException("Number should be positive");
            }

            int fact = 1;

            for(int count = number; count > 1; count--) {
                fact = fact * count;
            }

            System.out.println(fact);
        }
    }
}