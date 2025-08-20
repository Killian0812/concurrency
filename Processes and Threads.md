# Processes and Threads

**Processes** and **threads** are core units in concurrent programming. In Java, concurrency mainly uses **threads**, but **processes** matter too. Even on a single-core system, multiple processes/threads run via **time slicing**.

## Processes

A **process** has its own **execution environment** and **memory space**. What looks like one application may be several **cooperating processes**. **Inter Process Communication (IPC)** (e.g., **pipes**, **sockets**) lets processes communicate, even across systems. Java apps usually run as a single process but can create more with **ProcessBuilder**.

## Threads

**Threads** (sometimes called lightweight processes) share a process's **resources** (memory, files), making communication efficient but potentially problematic. Every Java app starts with a **main thread** and can create more. **Multithreading** is essential in Java. Every application has at least one thread — or several, if you count system threads **memory management** and **signal handling**.

Each thread is associated with an instance of the class **Thread**. There are two basic strategies for using Thread objects to create a concurrent application.

- To directly **control thread creation and management**, simply instantiate Thread each time the application needs to initiate an **asynchronous task**.
- To **abstract thread management** from the rest of your application, pass the application's tasks to an **executor**.

An application that creates an instance of Thread must provide the code that will run in that thread. There are two ways to do this:
- Provide a **Runnable** object. The Runnable interface defines a single method, run, meant to contain the code executed in the thread. The Runnable object is passed to the Thread constructor.
- Subclass Thread. The Thread class itself implements Runnable, though its run method does nothing. An application can subclass Thread, providing its own implementation of run.

The first way, which employs a Runnable object, is more general, because the Runnable object can subclass a class other than Thread. The second way is easier to use in simple applications, but is limited by the fact that your task class must be a descendant of Thread.