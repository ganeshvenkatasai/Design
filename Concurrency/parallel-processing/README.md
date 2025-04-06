# parallel-processing

```
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelProcessingExamples {

    public static void main(String[] args) throws Exception {
        // 1. Basic Thread Pool with ExecutorService
        executorServiceExample();

        // 2. ForkJoinPool for recursive tasks
        forkJoinExample();

        // 3. Parallel Streams
        parallelStreamExample();

        // 4. CompletableFuture for async pipelines
        completableFutureExample();
    }

    // ========== 1. ExecutorService (Fixed Thread Pool) ==========
    private static void executorServiceExample() throws InterruptedException {
        System.out.println("\n=== ExecutorService Example ===");
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Callable<String>> tasks = Arrays.asList(
            () -> { Thread.sleep(500); return "Task 1 completed"; },
            () -> { Thread.sleep(300); return "Task 2 completed"; },
            () -> { Thread.sleep(700); return "Task 3 completed"; }
        );

        // Execute all tasks and get futures
        List<Future<String>> futures = executor.invokeAll(tasks);
        
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }

    // ========== 2. ForkJoinPool (Work Stealing) ==========
    private static void forkJoinExample() {
        System.out.println("\n=== ForkJoinPool Example ===");
        ForkJoinPool pool = new ForkJoinPool();
        int[] numbers = IntStream.rangeClosed(1, 10000).toArray();
        
        Long result = pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
        System.out.println("Parallel sum: " + result);
    }

    static class SumTask extends RecursiveTask<Long> {
        private final int[] array;
        private final int start, end;
        private static final int THRESHOLD = 500;

        SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                return Arrays.stream(array, start, end + 1).asLongStream().sum();
            }
            int mid = (start + end) >>> 1;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid + 1, end);
            left.fork();
            return right.compute() + left.join();
        }
    }

    // ========== 3. Parallel Streams ==========
    private static void parallelStreamExample() {
        System.out.println("\n=== Parallel Stream Example ===");
        long sum = IntStream.rangeClosed(1, 1000000)
                          .parallel()
                          .filter(n -> n % 2 == 0)
                          .sum();
        System.out.println("Sum of even numbers: " + sum);
    }

    // ========== 4. CompletableFuture (Async Pipelines) ==========
    private static void completableFutureExample() throws Exception {
        System.out.println("\n=== CompletableFuture Example ===");
        CompletableFuture.supplyAsync(() -> fetchDataFromDB())
                        .thenApply(data -> transformData(data))
                        .thenAccept(transformed -> System.out.println("Final result: " + transformed))
                        .get(); // wait for completion
    }

    private static String fetchDataFromDB() {
        try { Thread.sleep(300); } catch (InterruptedException e) {}
        return "Raw Data";
    }

    private static String transformData(String data) {
        return data.toUpperCase() + " - Processed";
    }
}
```