package com.thread.demo.pool;

import java.util.Random;
import java.util.concurrent.*;

public class CallableToReturn {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(new Worker());
        int result = future.get();
        System.out.println("callable output is:" + result);

        executorService.shutdown();

        //lambda
        Future<Integer> futureLambda = executorService.submit(() -> new Random().nextInt());
        int result2 =futureLambda.get();
        System.out.println("callable2 output is:" + result);
    }

    static class Worker implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            return new Random().nextInt();
        }
    }
}
