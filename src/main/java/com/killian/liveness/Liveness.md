# Liveness
A concurrent application's ability to execute in a **timely manner** is known as its liveness. 
The most common kind of liveness problem: **deadlock**, and two other liveness problems: **starvation** and **livelock**.

*Starvation* and *livelock* are much less common a problem than deadlock.

**Starvation** occurs when a thread cannot gain regular access to shared resources and cannot make progress, often because other **greedy** threads monopolize those resources.

**Livelock** happens when threads continually respond to each other without making progress. Unlike deadlock, the threads are not blocked but remain active, repeatedly reacting to each other, preventing forward movement.


