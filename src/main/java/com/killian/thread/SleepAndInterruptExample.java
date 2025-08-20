package com.killian.thread;

// Thread.sleep causes the current thread to suspend execution for a specified period.
// This is an efficient means of making processor time available to the other threads of an application or other applications that might be running on a computer system. 
// The sleep method can also be used for pacing.
public class SleepAndInterruptExample {
    public static void main(String args[])
            throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        Thread mainThread = Thread.currentThread();

        // Second thread that interrupts the main thread
        Thread interrupter = new Thread(() -> {
            try {
                // Should only be able to print the first 2 strings
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                // Ignored but required try-catch as InterruptedException is a checked exception
                // of Thread.sleep()
            }
            mainThread.interrupt();
        });

        interrupter.start();

        for (String s : importantInfo) {
            // Cause current execution thread to sleep for 1 second
            Thread.sleep(1000);
            System.out.println(s);
        }
    }
}
