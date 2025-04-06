# threads

```
public class ThreadExamples {

    public static void main(String[] args) {
        // 1. Extending Thread Class
        Thread thread1 = new MyThread();
        thread1.start();

        // 2. Implementing Runnable Interface
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

        // 3. Lambda Runnable (Java 8+)
        Thread thread3 = new Thread(() -> {
            System.out.println("Lambda Runnable running!");
        });
        thread3.start();

        // 4. Thread Sleep Example
        new Thread(() -> {
            try {
                System.out.println("Thread sleeping for 2 seconds...");
                Thread.sleep(2000);
                System.out.println("Thread woke up!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 5. Synchronized Method Example
        Counter counter = new Counter();
        new Thread(() -> counter.increment()).start();
        new Thread(() -> System.out.println("Count: " + counter.getCount())).start();
    }

    // Example 1: Extending Thread Class
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread running!");
        }
    }

    // Example 2: Implementing Runnable Interface
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable running!");
        }
    }

    // Example 5: Synchronized Counter
    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }
}

```