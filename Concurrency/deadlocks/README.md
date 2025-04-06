# deadlocks

```
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class DeadlockExamples {

    // 1. Classic Deadlock Example
    static class ClassicDeadlock {
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();

        static void demonstrate() {
            Thread t1 = new Thread(() -> {
                synchronized (lock1) {
                    System.out.println("Thread 1: Holding lock 1...");
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                    System.out.println("Thread 1: Waiting for lock 2...");
                    synchronized (lock2) {
                        System.out.println("Thread 1: Acquired both locks!");
                    }
                }
            });

            Thread t2 = new Thread(() -> {
                synchronized (lock2) {
                    System.out.println("Thread 2: Holding lock 2...");
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                    System.out.println("Thread 2: Waiting for lock 1...");
                    synchronized (lock1) {
                        System.out.println("Thread 2: Acquired both locks!");
                    }
                }
            });

            t1.start();
            t2.start();
        }
    }

    // 2. Deadlock Prevention Techniques
    static class DeadlockPrevention {
        private static final Lock lockA = new ReentrantLock();
        private static final Lock lockB = new ReentrantLock();

        // Technique 1: Lock ordering
        static void lockOrdering() {
            Thread t1 = new Thread(() -> acquireLocksInOrder(lockA, lockB));
            Thread t2 = new Thread(() -> acquireLocksInOrder(lockA, lockB));
            t1.start();
            t2.start();
        }

        private static void acquireLocksInOrder(Lock first, Lock second) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try {
                gotFirstLock = first.tryLock(500, TimeUnit.MILLISECONDS);
                gotSecondLock = second.tryLock(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (gotFirstLock && gotSecondLock) {
                    System.out.println("Locks acquired in order!");
                } else {
                    if (gotFirstLock) first.unlock();
                    if (gotSecondLock) second.unlock();
                    System.out.println("Failed to acquire locks - releasing");
                }
            }
        }

        // Technique 2: Timeout backoff
        static void timeoutBackoff() {
            new Thread(() -> {
                while (true) {
                    if (lockA.tryLock()) {
                        try {
                            if (lockB.tryLock(300, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("Thread acquired both locks!");
                                    break;
                                } finally {
                                    lockB.unlock();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lockA.unlock();
                        }
                    }
                    // Random backoff before retry
                    try { Thread.sleep((long)(Math.random() * 1000)); } 
                    catch (InterruptedException e) {}
                }
            }).start();
        }
    }

    // 3. Deadlock Detection
    static class DeadlockDetector {
        static void detectDeadlocks() {
            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
            long[] threadIds = threadBean.findDeadlockedThreads();
            
            if (threadIds != null) {
                ThreadInfo[] infos = threadBean.getThreadInfo(threadIds);
                System.out.println("Deadlocked threads detected:");
                for (ThreadInfo info : infos) {
                    System.out.println(info.getThreadName() + 
                                     " blocked on: " + info.getLockName() +
                                     " owned by: " + info.getLockOwnerName());
                }
            } else {
                System.out.println("No deadlocks detected");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. Classic Deadlock Example ===");
        // ClassicDeadlock.demonstrate(); // Uncomment to see deadlock
        Thread.sleep(1000); // Give time for deadlock to occur
        
        System.out.println("\n=== 2. Deadlock Prevention ===");
        DeadlockPrevention.lockOrdering();
        DeadlockPrevention.timeoutBackoff();
        
        System.out.println("\n=== 3. Deadlock Detection ===");
        DeadlockDetector.detectDeadlocks();
    }
}
```