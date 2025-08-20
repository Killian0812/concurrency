package com.killian.thread;

public class ThreadExample extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from a ThreadExample!");
    }

    public static void main(String args[]) {
        (new ThreadExample()).start();
    }
}
