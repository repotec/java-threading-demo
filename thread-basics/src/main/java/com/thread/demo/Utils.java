package com.thread.demo;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static void doSleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
