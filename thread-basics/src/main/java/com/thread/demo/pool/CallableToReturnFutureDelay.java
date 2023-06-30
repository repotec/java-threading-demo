package com.thread.demo.pool;

import java.util.Random;
import java.util.concurrent.*;

public class CallableToReturnFutureDelay {
    public static void main(String[] args)  {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> output = executorService.submit(new WorkerDelay());
        try {
            System.out.println("output is:" + output.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            output.cancel(true);
        }

        executorService.shutdown();
    }

    static class WorkerDelay implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return new Random().nextInt();
        }
    }
}
