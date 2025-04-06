# mutex

```
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexExamples {

    // Shared resource
    private static int counter = 0;
    
    // 1. Using synchronized keyword (intrinsic lock)
    private static final Object lock = new Object();
    
    // 2. Using ReentrantLock (explicit lock)
    private static final Lock reentrantLock = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException {
        // Demonstrate all mutex approaches
        intrinsicLockExample();
        reentrantLockExample();
        tryLockExample();
    }

    // ===== 1. Intrinsic Lock (synchronized) =====
    private static void intrinsicLockExample() throws InterruptedException {
        counter = 0; // reset counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Intrinsic lock counter: " + counter);
    }

    // ===== 2. ReentrantLock (explicit lock) =====
    private static void reentrantLockExample() throws InterruptedException {
        counter = 0; // reset counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                try {
                    counter++;
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                try {
                    counter++;
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("ReentrantLock counter: " + counter);
    }

    // ===== 3. tryLock with timeout =====
    private static void tryLockExample() {
        new Thread(() -> {
            if (reentrantLock.tryLock()) {
                try {
                    System.out.println("Thread 1 acquired the lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                System.out.println("Thread 1 couldn't acquire the lock");
            }
        }).start();
        
        new Thread(() -> {
            try {
                // Wait up to 500ms for the lock
                if (reentrantLock.tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 2 acquired the lock");
                    } finally {
                        reentrantLock.unlock();
                    }
                } else {
                    System.out.println("Thread 2 gave up waiting");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
```