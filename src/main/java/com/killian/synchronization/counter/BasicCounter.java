package com.killian.synchronization.counter;

public class BasicCounter implements Counter {
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
