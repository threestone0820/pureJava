package three.stone.base;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class AsyncUtils {

    public static <T> CompletionStage<List<T>> join(List<CompletionStage<T>> stages) {
        CompletionStage<List<T>> joinedStage = CompletableFuture.completedFuture(new ArrayList<>());

        for (CompletionStage<T> stage : stages) {
            joinedStage = joinedStage.thenCombine(stage, (acc, value) -> {
                acc.add(value);
                return acc;
            });
        }

        return joinedStage;
    }

    public static <T> CompletionStage<Map<String, T>> joinMap(Map<String, CompletionStage<T>> stages) {
        CompletionStage<Map<String, T>> result = CompletableFuture.completedFuture(new HashMap<>());
        for (Map.Entry<String, CompletionStage<T>> entry : stages.entrySet()) {
            String key = entry.getKey();
            result = result.thenCombine(entry.getValue(),
                    (Map<String, T> acc, T val) -> {
                        acc.put(key, val);
                        return acc;
                    });
        }
        return result;
    }

    public static <T> CompletionStage<List<T>> allOf(List<CompletionStage<T>> stages) {
        List<CompletableFuture<T>> futures = stages.stream().map(t -> t.toCompletableFuture()).collect(Collectors.toList());
        CompletableFuture<T>[] array = futures.stream().toArray(CompletableFuture[]::new);
        return CompletableFuture.allOf(array).thenApply(aVoid ->
                futures.stream().map(future -> future.join()).collect(Collectors.toList()));
    }

    public static <T> CompletionStage<T> anyOf(List<CompletionStage<T>> stages) {
        CompletableFuture[] array = stages.stream()
                .map(CompletionStage::toCompletableFuture)
                .toArray(CompletableFuture[]::new);
        return CompletableFuture.anyOf(array)
                .thenApply(result -> (T) result);
    }

    public static <T> CompletionStage<List<T>> failFastJoin2(List<CompletionStage<T>> stages) {
        CompletableFuture<List<T>> firstExceptionalStage = new CompletableFuture<>();
        stages = stages.stream().map(s -> s.whenComplete((r, t) -> {
            if (t != null) {
                firstExceptionalStage.completeExceptionally(t);
            }
        })).collect(Collectors.toList());

        return anyOf(ImmutableList.of(firstExceptionalStage, allOf(stages)));
    }

    public static <T> CompletionStage<List<T>> failFastJoin(List<CompletionStage<T>> stages) {
        if (stages.size() == 0) {
            return CompletableFuture.completedFuture(new ArrayList<T>());
        }
        // If any of the stages completes exceptionally, we want to propagate this exception -
        // regardless of whether other stages completed or not
        @SuppressWarnings("SuspiciousToArrayCall")
        CompletionStage<List<T>> joinedStages = CompletableFuture
                .anyOf(stages.toArray(new CompletableFuture<?>[stages.size()]))
                .thenApply(val -> new ArrayList<T>());
        for (CompletionStage<T> nextStage : stages) {
            joinedStages = joinedStages.thenCompose(resultList ->
                    nextStage.thenApply(
                            value -> {
                                resultList.add(value);
                                return resultList;
                            }
                    ));
        }
        return joinedStages;
    }

}
