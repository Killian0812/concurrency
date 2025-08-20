# Processes and Threads

**Processes** and **threads** are core units in concurrent programming. In Java, concurrency mainly uses **threads**, but **processes** matter too. Even on a single-core system, multiple processes/threads run via **time slicing**.

## Processes

A **process** has its own **execution environment** and **memory space**. What looks like one application may be several **cooperating processes**. **Inter Process Communication (IPC)** (e.g., **pipes**, **sockets**) lets processes communicate, even across systems. Java apps usually run as a single process but can create more with **ProcessBuilder**.

## Threads

**Threads** (sometimes called lightweight processes) share a process's **resources** (memory, files), making communication efficient but potentially problematic. Every Java app starts with a **main thread** and can create more. **Multithreading** is essential in Java. Every application has at least one thread — or several, if you count system threads **memory management** and **signal handling**.
