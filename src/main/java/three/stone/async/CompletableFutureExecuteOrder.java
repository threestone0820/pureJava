package three.stone.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExecuteOrder {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> base = new CompletableFuture<>();
        CompletableFuture<String> future =
                base.thenApply(
                        s -> {
                            System.out.println("2");
                            return s + " 2";
                        });
        base.thenAccept(s -> System.out.println("a")).thenAccept(aVoid -> System.out.println("b"));
        base.thenAccept(s -> System.out.println("c")).thenAccept(aVoid -> System.out.println("d"));
        base.complete("1");
        System.out.println("three.stone.base result: {}" + base.get());
        System.out.println("future result: {}" +  future.get());
    }
}
