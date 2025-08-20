package com.killian.thread;

public class RunnableExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from a RunnableExample!");
    }

    public static void main(String args[]) {
        Thread thread = new Thread(new RunnableExample());
        thread.start();
    }
}
