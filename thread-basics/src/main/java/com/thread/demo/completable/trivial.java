package com.thread.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class trivial {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        completableFuture.complete("Future's Result");
        System.out.println(completableFuture.isDone());
        String result = completableFuture.get();
        System.out.println(result);
    }
}
