# locks

```
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class LockExamples {
    private static int sharedCounter = 0;
    
    // 1. ReentrantLock
    private static final Lock reentrantLock = new ReentrantLock();
    
    // 2. ReadWriteLock
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    // 3. StampedLock
    private static final StampedLock stampedLock = new StampedLock();
    
    // 4. Condition Variables
    private static final Lock conditionLock = new ReentrantLock();
    private static final Condition notEmpty = conditionLock.newCondition();
    private static final Condition notFull = conditionLock.newCondition();
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final int CAPACITY = 5;

    public static void main(String[] args) throws InterruptedException {
        // Demo all lock types
        reentrantLockDemo();
        readWriteLockDemo();
        stampedLockDemo();
        conditionVariablesDemo();
    }

    // ===== 1. ReentrantLock =====
    private static void reentrantLockDemo() throws InterruptedException {
        Runnable task = () -> {
            reentrantLock.lock();
            try {
                for (int i = 0; i < 1000; i++) {
                    sharedCounter++;
                }
            } finally {
                reentrantLock.unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("ReentrantLock result: " + sharedCounter);
        sharedCounter = 0; // reset
    }

    // ===== 2. ReadWriteLock =====
    private static void readWriteLockDemo() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Writers
        executor.submit(() -> {
            readWriteLock.writeLock().lock();
            try {
                Thread.sleep(1000);
                sharedCounter = 42;
                System.out.println("Write: " + sharedCounter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        // Readers
        Runnable readTask = () -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Read: " + sharedCounter);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        };
        
        executor.submit(readTask);
        executor.submit(readTask);
        
        executor.shutdown();
    }

    // ===== 3. StampedLock =====
    private static void stampedLockDemo() {
        // Optimistic read
        Runnable optimisticReadTask = () -> {
            long stamp = stampedLock.tryOptimisticRead();
            int value = sharedCounter;
            if (!stampedLock.validate(stamp)) {
                stamp = stampedLock.readLock();
                try {
                    value = sharedCounter;
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }
            System.out.println("Optimistic read: " + value);
        };

        // Write
        Runnable writeTask = () -> {
            long stamp = stampedLock.writeLock();
            try {
                sharedCounter = 100;
                System.out.println("Write: " + sharedCounter);
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        };

        new Thread(optimisticReadTask).start();
        new Thread(writeTask).start();
        new Thread(optimisticReadTask).start();
    }

    // ===== 4. Condition Variables =====
    private static void conditionVariablesDemo() {
        // Producer
        new Thread(() -> {
            conditionLock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (buffer.size() == CAPACITY) {
                        notFull.await();
                    }
                    buffer.add(i);
                    System.out.println("Produced: " + i);
                    notEmpty.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                conditionLock.unlock();
            }
        }).start();

        // Consumer
        new Thread(() -> {
            conditionLock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (buffer.isEmpty()) {
                        notEmpty.await();
                    }
                    int value = buffer.poll();
                    System.out.println("Consumed: " + value);
                    notFull.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                conditionLock.unlock();
            }
        }).start();
    }
}
```