package three.stone.java;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CompletableExecuteThread {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(1, r -> new Thread(r, "custom thread"));
        CompletableFuture<String> stage = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " one");
            return "one";
        }).thenApplyAsync(s -> {
            System.out.println(Thread.currentThread() + " two");
            return s + " two";
        }, executor).thenApply(s -> {
            System.out.println(Thread.currentThread() + " three");
            return s;
        });

        while (System.in.read() != 0) {

        }
    }
}
