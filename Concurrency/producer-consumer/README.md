# producer-consumer

```
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerExample {
    private static final int BUFFER_SIZE = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();
    
    // Semaphores for synchronization
    private static Semaphore fillCount = new Semaphore(0);  // items produced
    private static Semaphore emptyCount = new Semaphore(BUFFER_SIZE);  // remaining space
    private static Semaphore mutex = new Semaphore(1);  // for mutual exclusion
    
    public static void main(String[] args) {
        // Create multiple producer and consumer threads
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(), "Producer-" + i).start();
            new Thread(new Consumer(), "Consumer-" + i).start();
        }
    }
    
    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {  // each producer produces 5 items
                try {
                    emptyCount.acquire();  // wait if buffer is full
                    mutex.acquire();     // acquire lock
                    
                    // Produce item
                    int item = (int)(Math.random() * 100);
                    buffer.add(item);
                    System.out.println(Thread.currentThread().getName() 
                        + " produced: " + item + " | Buffer size: " + buffer.size());
                    
                    mutex.release();
                    fillCount.release();  // increment fill count
                    
                    Thread.sleep(500);  // simulate time to produce
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {  // each consumer consumes 5 items
                try {
                    fillCount.acquire();  // wait if buffer is empty
                    mutex.acquire();      // acquire lock
                    
                    // Consume item
                    int item = buffer.remove();
                    System.out.println(Thread.currentThread().getName() 
                        + " consumed: " + item + " | Buffer size: " + buffer.size());
                    
                    mutex.release();
                    emptyCount.release();  // increment empty count
                    
                    Thread.sleep(1000);  // simulate time to consume
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```