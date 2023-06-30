package com.thread.demo.completable;

import com.thread.demo.Utils;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableThreadBoolSupplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
                @Override
                public String get() {
                    System.out.println(Thread.currentThread().getName());
                    Utils.doSleep(1);
                    return "Result of the asynchronous computation";
                }
            }
        , executor);

        String result = future.get();
        System.out.println("result:" + result);

        CompletableFuture<String> futureLambda = CompletableFuture.supplyAsync(()-> {
                    System.out.println(Thread.currentThread().getName());
                    Utils.doSleep(1);
                    return "Result of the asynchronous computation with lambda";
                }
        , executor);

        result = future.get();
        System.out.println("result:" + result);
    }
}
