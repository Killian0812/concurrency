package com.killian.thread;

@SuppressWarnings("CallToPrintStackTrace")
public class JoinExample {

    static void exec(int millis) {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> exec(1000));
        Thread thread2 = new Thread(() -> exec(2000));

        thread1.start();
        thread2.start();

        // In main thread, wait for thread1 to finish, thread2 still be running in
        // parallel
        try {
            thread1.join();
            // thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread1.getName() + " has finished.");
    }
}
