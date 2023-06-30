package com.thread.demo.completable;

import com.thread.demo.Utils;

import java.util.concurrent.*;

public class CompletableThenApplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<String> future = CompletableFuture
            .supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName());
                    Utils.doSleep(1);
                    return "hello";
                }, executor)
           .thenApplyAsync(result -> {
                System.out.println(Thread.currentThread().getName());
                Utils.doSleep(1);
                return result + " " + "world";
        }, executor);

        String result = future.get();
        System.out.println("result:" + result);

    }
}
