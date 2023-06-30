package com.thread.demo.completable;


import com.thread.demo.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableThenAccept {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<Void> future = CompletableFuture
            .supplyAsync(()->{
                Utils.doSleep(1);
                return "hello";
            }, executor)
            .thenAccept(result -> {
                Utils.doSleep(1);
                System.out.println(result + " " + "world");
            });

        executor.shutdown();
    }
}
