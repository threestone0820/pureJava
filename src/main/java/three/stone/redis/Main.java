package three.stone.redis;

import io.lettuce.core.codec.StringCodec;
import three.stone.exception.WrapNonRuntimeException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RedisAsyncClientApi<String, String> connection = AsyncRedisProxy.create("172.23.172.105", 6379, new StringCodec());
        connection.set("test_key", "test_value")
                .thenApply(result -> {
                    System.out.println("set result " + result);
                    connection.get("test_key")
                            .thenAccept(value -> {
                                System.out.println("get result " + value);
                            });
                    return null;
                })
                .exceptionally(e -> {
                    System.out.println(e);
                    throw new WrapNonRuntimeException(e);
                });

        while (true) {
            Thread.sleep(10000);
            break;
        }
    }
}