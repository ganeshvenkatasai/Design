# semaphores

```
import java.util.concurrent.Semaphore;

public class SemaphoreExamples {

    public static void main(String[] args) throws InterruptedException {
        // Example 1: Basic Semaphore (1 permit = mutex)
        Semaphore binarySemaphore = new Semaphore(1); // Binary semaphore (acts like a mutex)

        new Thread(() -> {
            try {
                binarySemaphore.acquire();
                System.out.println("Thread 1 acquired the semaphore (lock)");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                binarySemaphore.release();
                System.out.println("Thread 1 released the semaphore");
            }
        }).start();

        new Thread(() -> {
            try {
                binarySemaphore.acquire();
                System.out.println("Thread 2 acquired the semaphore (lock)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                binarySemaphore.release();
                System.out.println("Thread 2 released the semaphore");
            }
        }).start();

        // Example 2: Semaphore with Multiple Permits (Resource Pool)
        Semaphore poolSemaphore = new Semaphore(3); // Allow 3 concurrent accesses

        for (int i = 1; i <= 5; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + threadId + " waiting for permit");
                    poolSemaphore.acquire();
                    System.out.println("Thread " + threadId + " acquired permit");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    poolSemaphore.release();
                    System.out.println("Thread " + threadId + " released permit");
                }
            }).start();
        }

        // Example 3: tryAcquire() (Non-blocking attempt)
        new Thread(() -> {
            if (poolSemaphore.tryAcquire()) {
                try {
                    System.out.println("Thread got permit immediately!");
                } finally {
                    poolSemaphore.release();
                }
            } else {
                System.out.println("Thread couldn't get permit, doing something else");
            }
        }).start();

        // Example 4: Fair Semaphore (First-come-first-serve)
        Semaphore fairSemaphore = new Semaphore(1, true); // Fair semaphore
    }
}
```