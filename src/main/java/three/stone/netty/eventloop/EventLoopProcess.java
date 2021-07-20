package three.stone.netty.eventloop;

import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.CompletableFuture;

public class EventLoopProcess {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> stage = new CompletableFuture<>();
        NioEventLoopGroup executors = new NioEventLoopGroup(1);
        executors.submit(() -> {
            stage.thenAccept(s -> {
                System.out.println(Thread.currentThread());
                System.out.println("Task1: " + s);
            });
        });

        executors.submit(() -> {
            stage.thenAccept(s -> {
                System.out.println(Thread.currentThread());
                System.out.println("Task2: " + s);
            });
        });

        Thread.sleep(100 * 1000);
        stage.complete("hello");
    }
}
