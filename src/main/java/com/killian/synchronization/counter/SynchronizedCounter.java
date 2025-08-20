package com.killian.synchronization.counter;

public class SynchronizedCounter implements Counter {
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