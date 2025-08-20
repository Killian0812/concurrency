package com.killian.synchronization;

public class IntrinsicLockExample {
    static class CounterOneLock {
        private int counterA = 0;
        private int counterB = 0;

        public synchronized void incA() {
            counterA++;
        }

        public synchronized void incB() {
            counterB++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CounterOneLock oneLock = new CounterOneLock();

        Runnable taskA = () -> {
            for (int i = 0; i < 10_000_000; i++) {
                oneLock.incA();
            }
        };

        Runnable taskB = () -> {
            for (int i = 0; i < 10_000_000; i++) {
                oneLock.incB();
            }
        };

        Thread t1 = new Thread(taskA);
        Thread t2 = new Thread(taskB);

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("Counter A: " + oneLock.counterA + " ; Counter B: " + oneLock.counterB);
        System.out.println("Elapsed time: " + (end - start) + " ms");
    }
}
