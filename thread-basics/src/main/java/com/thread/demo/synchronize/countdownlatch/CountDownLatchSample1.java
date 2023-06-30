package com.thread.demo.synchronize.countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountDownLatchSample1 {
    public static void main(String[] args) throws InterruptedException {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
                                     .limit(5)
                                     .collect(toList());

        workers.forEach(thread -> thread.start());
        countDownLatch.await();
        outputScraper.add("Latch released");
    }
}

class Worker implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doSomeWork();
        outputScraper.add("Counted down");
        countDownLatch.countDown();
    }

    void doSomeWork(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}