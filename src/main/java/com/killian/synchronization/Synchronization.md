# Synchronization
Threads communicate primarily by sharing access to fields and the objects reference fields refer to. This form of communication is extremely efficient, but makes two kinds of errors possible: **thread interference** and **memory consistency** errors. The tool needed to prevent these errors is synchronization.

**However**, synchronization can introduce **thread contention**, which occurs when two or more threads try to access the same resource **simultaneously** and cause the Java runtime to execute one or more threads *more slowly*, or even *suspend* their execution. **Starvation** and **livelock** are forms of thread contention. See the section Liveness for more information.

A simple **strategy for preventing** thread interference and memory consistency errors: if an object *is visible to more than one thread*, all reads or writes to that object's variables are done through **synchronized** methods (Exception: final fields).
This strategy is effective, but can present problems with liveness.