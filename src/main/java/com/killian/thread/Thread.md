Each thread is associated with an instance of the class **Thread**. There are two basic strategies for using Thread objects to create a concurrent application.

- To directly **control thread creation and management**, simply instantiate Thread each time the application needs to initiate an **asynchronous task**.
- To **abstract thread management** from the rest of your application, pass the application's tasks to an **executor**.

An application that creates an instance of Thread must provide the code that will run in that thread. There are two ways to do this:
- Provide a **Runnable** object. The Runnable interface defines a single method, run, meant to contain the code executed in the thread. The Runnable object is passed to the Thread constructor.
- Subclass Thread. The Thread class itself implements Runnable, though its run method does nothing. An application can subclass Thread, providing its own implementation of run.

The first way, which employs a Runnable object, is more general, because the Runnable object can subclass a class other than Thread. The second way is easier to use in simple applications, but is limited by the fact that your task class must be a descendant of Thread.

An **interrupt** is an indication to a thread that it should stop what it is doing and do something else. It's up to the programmer to decide exactly how a thread responds to an interrupt, but it is very common for the thread to **terminate**.

The **join** method allows one thread to **wait for the completion** of another. If t is a Thread object is currently executing:
t.join();
causes the current thread to pause execution until t's thread terminates.

Overloads of join allow the programmer to specify a waiting period. However, as with sleep, join is dependent on the OS for timing, so you should not assume that join will wait exactly as long as you specify.