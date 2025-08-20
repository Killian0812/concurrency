package com.killian.synchronization;

public class SoleLocksExample {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    static class CounterTwoLocks {
        private int counterA = 0;
        private int counterB = 0;

        public void incA() {
            synchronized (lockA) {
                counterA++;
            }
        }

        public void incB() {
            synchronized (lockB) {
                counterB++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CounterTwoLocks twoLocks = new CounterTwoLocks();

        Runnable taskA = () -> {
            for (int i = 0; i < 10_000_000; i++) {
                twoLocks.incA();
            }
        };

        Runnable taskB = () -> {
            for (int i = 0; i < 10_000_000; i++) {
                twoLocks.incB();
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
        System.out.println("Counter A: " + twoLocks.counterA + " ; Counter B: " + twoLocks.counterB);
        System.out.println("Elapsed time: " + (end - start) + " ms");
    }
}
