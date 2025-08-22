package com.killian.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private int count = 0;
    private final int MAX = 5;

    // If fair = true => Thread that waited for longest period get the lock
    private final Lock lock = new ReentrantLock(true);
    // Separated wait sets
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    // Producer adds an item
    public void put() throws InterruptedException {
        lock.lock();
        try {
            while (count == MAX) {
                System.out.println(Thread.currentThread().getName() + " waits: buffer full");
                notFull.await(); // wait until space is available
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " produced => count = " + count);
            notEmpty.signal(); // signal consumers
        } finally {
            lock.unlock();
        }
    }

    // Consumer removes an item
    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName() + " waits: buffer empty");
                notEmpty.await(); // wait until item is available
            }
            count--;
            System.out.println(Thread.currentThread().getName() + " consumed => count = " + count);
            notFull.signal(); // signal producers
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample buffer = new ReentrantLockExample();

        // Producer thread
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 100; i++) {
                    buffer.put();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Consumer thread
        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 100; i++) {
                    buffer.take();
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer-1").start();
        new Thread(consumer, "Consumer-2").start();
    }
}
