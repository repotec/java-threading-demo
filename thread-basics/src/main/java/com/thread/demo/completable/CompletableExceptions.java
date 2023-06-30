package com.thread.demo.completable;

import com.thread.demo.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableExceptions {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(()-> {
                Utils.doSleep(1);
                doSomething();
                return "hello";
            }
        ).thenApply((value)->value + " " + "world")
         .thenAccept(System.out::println).join();
    }

    public static void doSomething(){
        throw new RuntimeException("something goes wrong");
    }
}
