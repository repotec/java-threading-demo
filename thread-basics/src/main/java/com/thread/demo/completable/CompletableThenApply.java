package com.thread.demo.completable;

import com.thread.demo.Utils;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableThenApply {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<String> future = CompletableFuture
            .supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName());
                    Utils.doSleep(1);
                    return "hello";
                }, executor)
           .thenApply(result -> {
                System.out.println(Thread.currentThread().getName());
                Utils.doSleep(1);
                return result + " " + "world";
        });

        String result = future.get();
        System.out.println("result:" + result);
    }
}
