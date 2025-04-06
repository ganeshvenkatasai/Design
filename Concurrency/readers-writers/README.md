# readers-writers

```
import java.util.concurrent.Semaphore;

public class ReadersWritersExample {
    private static int sharedData = 0;
    private static int readersCount = 0;
    
    // Semaphores for synchronization
    private static Semaphore readLock = new Semaphore(1);
    private static Semaphore writeLock = new Semaphore(1);
    
    public static void main(String[] args) {
        // Create multiple reader and writer threads
        for (int i = 0; i < 5; i++) {
            new Thread(new Reader(), "Reader-" + i).start();
            new Thread(new Writer(), "Writer-" + i).start();
        }
    }
    
    static class Reader implements Runnable {
        @Override
        public void run() {
            try {
                // Acquire read lock
                readLock.acquire();
                readersCount++;
                if (readersCount == 1) {
                    writeLock.acquire(); // Block writers if first reader
                }
                readLock.release();
                
                // Reading section
                System.out.println(Thread.currentThread().getName() 
                    + " is READING: " + sharedData);
                Thread.sleep(1000); // Simulate reading time
                
                // Release read lock
                readLock.acquire();
                readersCount--;
                if (readersCount == 0) {
                    writeLock.release(); // Allow writers if no readers left
                }
                readLock.release();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Writer implements Runnable {
        @Override
        public void run() {
            try {
                // Acquire write lock
                writeLock.acquire();
                
                // Writing section
                sharedData++;
                System.out.println(Thread.currentThread().getName() 
                    + " is WRITING: " + sharedData);
                Thread.sleep(2000); // Simulate writing time
                
                // Release write lock
                writeLock.release();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```