package three.stone.redis;

import io.lettuce.core.codec.StringCodec;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        RedisAsyncClientApi<String, String> connection = RedisAsyncProxy.create("172.23.172.105", 6379, new StringCodec());
        RedisTransactionApi<String, String> transaction = connection.transaction();
        transaction.get("test_key");
        transaction.set("test_key", "dummy_value2");
        transaction.get("test_key");

        transaction.execute()
                .thenApply(results -> {
                    String o1 = results.get(0);
                    String o2 = results.get(1);
                    String o3 = results.get(2);
                    System.out.println(o1);
                    System.out.println(o2);
                    System.out.println(o3);
                    return null;
                });

        if (System.in.read() < 10) {

        }
    }
}