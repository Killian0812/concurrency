package com.killian.synchronization;

@SuppressWarnings("CallToPrintStackTrace")
public class InterferenceExample {

    private static interface CounterInterface {
        void increment();

        int value();
    }

    private static class Counter implements CounterInterface {
        private int c = 0;

        @Override
        public void increment() {
            c++;
        }

        @Override
        public int value() {
            return c;
        }
    }

    private static class SyncCounter implements CounterInterface {
        private int c = 0;

        @Override
        public synchronized void increment() {
            c++;
        }

        @Override
        public synchronized int value() {
            return c;
        }
    }

    private static void interferenceCounter(CounterInterface counter) {
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
        Counter counter = new Counter();
        SyncCounter syncCounter = new SyncCounter();

        System.out.println("Counter without synchronization:");
        interferenceCounter(counter);
        System.out.println("Counter with synchronization:");
        interferenceCounter(syncCounter);
    }
}
