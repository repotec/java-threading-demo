package com.thread.demo.completable;

import com.thread.demo.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableSupplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
                @Override
                public String get() {
                    Utils.doSleep(1);
                    return "Result of the asynchronous computation";
                }
            }
        );

        String result = future.get();
        System.out.println("result:" + result);

        CompletableFuture<String> futureLambda = CompletableFuture.supplyAsync(()-> {
                    Utils.doSleep(1);
                    return "Result of the asynchronous computation with lambda";
                }
        );

        result = future.get();
        System.out.println("result:" + result);
    }
}
