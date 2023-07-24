package com.thread.demo.management;

public class ThreadGroupForThreads {
    public static void main(String[] args) {
        ThreadGroup threadGroup1 = new ThreadGroup("custom-group-1");
        threadGroup1.setMaxPriority(Thread.NORM_PRIORITY);

        Thread t1 = new Thread(threadGroup1, ()-> {});
        Thread t2 = new Thread(threadGroup1, ()-> {});
        Thread t3 = new Thread(threadGroup1, ()-> {});

        //the priority of thread that inside a ThreadGroup can only be reduced and not decreased
        t1.start();
        t1.setPriority(Thread.MIN_PRIORITY);
        printThreadOptions(t1);

        t2.start();
        t2.setPriority(Thread.MIN_PRIORITY);
        printThreadOptions(t2);

        t3.start();
        t3.setPriority(Thread.MAX_PRIORITY);
        printThreadOptions(t3);
    }

    public static void printThreadOptions(Thread t){
        System.out.println(t.getName() + "|" + t.getThreadGroup().getName() + "|" + t.getPriority());
    }
}
