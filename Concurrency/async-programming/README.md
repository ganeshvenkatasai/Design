# async-programming

```
import java.util.concurrent.*;
import java.util.function.*;

public class AsyncProgrammingExamples {

    // ========== 1. CompletableFuture Basics ==========
    public static void completableFutureDemo() throws Exception {
        System.out.println("\n=== CompletableFuture Basics ===");
        
        // Simple async task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            return "Hello";
        });
        
        // Chain operations
        future.thenApply(s -> s + " World")
              .thenAccept(System.out::println)
              .get(); // Wait for completion
        
        // Combine futures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Future1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Future2");
        
        future1.thenCombine(future2, (res1, res2) -> res1 + " + " + res2)
               .thenAccept(System.out::println)
               .get();
    }

    // ========== 2. Exception Handling ==========
    public static void exceptionHandlingDemo() throws Exception {
        System.out.println("\n=== Exception Handling ===");
        
        CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) throw new RuntimeException("Oops!");
            return "Success";
        })
        .exceptionally(ex -> "Recovered: " + ex.getMessage())
        .thenAccept(System.out::println)
        .get();
        
        // Handle both success and failure
        CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) throw new RuntimeException("Error!");
            return "Data";
        })
        .handle((res, ex) -> ex != null ? "Fallback" : res)
        .thenAccept(System.out::println)
        .get();
    }

    // ========== 3. Async HTTP Calls ==========
    public static void httpClientDemo() throws Exception {
        System.out.println("\n=== Async HTTP ===");
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/google"))
                .build();
        
        CompletableFuture<String> response = client.sendAsync(request, 
                HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
        
        System.out.println("Response (first 50 chars): " + 
            response.get().substring(0, 50) + "...");
    }

    // ========== 4. Combining Futures ==========
    public static void combiningFuturesDemo() throws Exception {
        System.out.println("\n=== Combining Futures ===");
        
        CompletableFuture<String> userProfile = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(300); } catch (InterruptedException e) {}
            return "User Profile Data";
        });
        
        CompletableFuture<String> orderHistory = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(400); } catch (InterruptedException e) {}
            return "Order History";
        });
        
        // Combine when both complete
        userProfile.thenAcceptBoth(orderHistory, (profile, orders) -> 
            System.out.println("Combined: " + profile + " + " + orders))
            .get();
        
        // Get first completed
        CompletableFuture.anyOf(userProfile, orderHistory)
            .thenAccept(result -> 
                System.out.println("First completed: " + result))
            .get();
    }

    // ========== 5. Virtual Threads (Java 21+) ==========
    public static void virtualThreadsDemo() throws Exception {
        System.out.println("\n=== Virtual Threads ===");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> future = executor.submit(() -> {
                Thread.sleep(500);
                return "Virtual thread result";
            });
            
            System.out.println(future.get());
        }
    }

    // ========== 6. Reactive Streams ==========
    public static void reactiveStreamsDemo() {
        System.out.println("\n=== Reactive Streams ===");
        
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        
        Subscriber<String> subscriber = new Subscriber<>() {
            private Subscription subscription;
            
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }
            
            @Override
            public void onNext(String item) {
                System.out.println("Received: " + item);
                subscription.request(1);
            }
            
            @Override
            public void onError(Throwable throwable) {
                System.err.println("Error: " + throwable.getMessage());
            }
            
            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };
        
        publisher.subscribe(subscriber);
        publisher.submit("Data Item 1");
        publisher.submit("Data Item 2");
        publisher.close();
    }

    public static void main(String[] args) throws Exception {
        completableFutureDemo();
        exceptionHandlingDemo();
        // httpClientDemo();  // Uncomment to test (requires internet)
        combiningFuturesDemo();
        virtualThreadsDemo();
        reactiveStreamsDemo();
    }
}
```