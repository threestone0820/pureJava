package three.stone.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureThread {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ExecutorService threadPool2 = Executors.newFixedThreadPool(2);

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread());
            return "Beijing";
        }, threadPool).thenApplyAsync(s -> {
            System.out.println("Apply execute:" + Thread.currentThread());
            String result = "Hello " + s;
            System.out.println(result);
            return result;
        }, threadPool2);

        Thread.sleep(2000);
        System.out.println(Thread.currentThread());
    }
}
