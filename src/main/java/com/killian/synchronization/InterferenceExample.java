package com.killian.synchronization;

import com.killian.synchronization.counter.BasicCounter;
import com.killian.synchronization.counter.Counter;
import com.killian.synchronization.counter.SynchronizedCounter;

@SuppressWarnings("CallToPrintStackTrace")
public class InterferenceExample {
    private static void interferenceCounter(Counter counter) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                counter.increment();
            }
        });

        long startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should not result in 2000
        System.out.println(counter.value());
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static void main(String[] args) {
        System.out.println("Counter without synchronization:");
        interferenceCounter(new BasicCounter());
        System.out.println("Counter with synchronization:");
        interferenceCounter(new SynchronizedCounter());
    }
}
