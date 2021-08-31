package three.stone.basis;

import three.stone.base.AsyncUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class CompletionTest {
    public static void main(String[] args) {
        test(Arrays.asList(() -> booleanStage(), () -> booleanStage()));
        while (true) {

        }
    }

    private static void test(List<Supplier<CompletionStage<Boolean>>> stages) {
        stages.get(0).get()
                .thenCompose(result -> {
                    if (result) {
                        throw new RuntimeException("finished");
                    }

                    return stages.get(1).get();
                })
                .thenCompose(result2 -> {
                    System.out.println("execute stage2 result2, " + result2);
                    return CompletableFuture.completedFuture(null);
                })
                .exceptionally((e) -> {
                    System.out.println(e);
                    return CompletableFuture.completedFuture(null);
                });
    }

    private static CompletionStage<Boolean> booleanStage() {
        boolean result = true;
        int i = ThreadLocalRandom.current().nextInt(100);
        if (i < 20) {
            result = false;
        }
        System.out.println("execute boolean stage " + result);

        return CompletableFuture.completedFuture(result);
    }
}
