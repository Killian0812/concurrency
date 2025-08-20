# Synchronization
Threads communicate primarily by sharing access to fields and the objects reference fields refer to. This form of communication is extremely efficient, but makes two kinds of errors possible: **thread interference** and **memory consistency** errors. The tool needed to prevent these errors is synchronization.

**However**, synchronization can introduce **thread contention**, which occurs when two or more threads try to access the same resource **simultaneously** and cause the Java runtime to execute one or more threads *more slowly*, or even *suspend* their execution. **Starvation** and **livelock** are forms of thread contention. See the section Liveness for more information.

A simple **strategy for preventing** thread interference and memory consistency errors: if an object *is visible to more than one thread*, all reads or writes to that object's variables are done through **synchronized** methods (Exception: final fields).
This strategy is effective, but can present problems with liveness.

### Locks In Synchronized Methods
When a thread invokes a synchronized method, it automatically acquires the **intrinsic lock** for that method's object and releases it when the method returns. The lock release occurs even if the return was caused by an uncaught exception.
When a static synchronized method is invoked, the thread acquires the intrinsic lock for the *Class object* associated with the class. Thus access to class's static fields is controlled by a lock that's distinct from the lock for any instance of the class.

### Reentrant Synchronization
Recall that a thread cannot acquire a lock owned by another thread. But a thread can acquire a lock that it already owns. Allowing a thread to acquire the same lock more than once enables reentrant synchronization. This describes a situation where synchronized code, directly or indirectly, invokes a method that also contains synchronized code, and both sets of code use the same lock. Without reentrant synchronization, synchronized code would have to take many additional precautions to avoid having a thread cause itself to block.
