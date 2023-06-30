package com.thread.demo.completable;

import com.thread.demo.Utils;

import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableRunAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    Utils.doSleep(1);
                    System.out.println("I'll run in a separate thread than the main thread.");
                }
            }
        );

        // Block and wait for the future to complete
        completableFuture.get();

        CompletableFuture<Void> future = CompletableFuture.runAsync(()-> {
                    Utils.doSleep(1);
                    System.out.println("I'll run in a separate thread than the main thread.");
               }
        );

        future.get();
    }
}
