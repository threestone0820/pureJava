package three.stone.redis;

import three.stone.base.EventLoop;
import io.lettuce.core.resource.EventLoopGroupProvider;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.TimeUnit;

public class RedisEventLoopGroupProvider implements EventLoopGroupProvider {
    @Override
    public <T extends EventLoopGroup> T allocate(Class<T> type) {
        if (!type.equals(EventLoop.getEventLoopGroupClass())) {
            throw new IllegalArgumentException("Wrong event loop group type " + type);
        }
        //noinspection unchecked
        return (T) EventLoop.getEventLoopGroup();
    }

    @Override
    public int threadPoolSize() {
        return 1;
    }

    @Override
    public Future<Boolean> release(EventExecutorGroup eventLoopGroup, long quietPeriod, long timeout, TimeUnit unit) {
        return successPromise();
    }

    @Override
    public Future<Boolean> shutdown(long quietPeriod, long timeout, TimeUnit timeUnit) {
        return successPromise();
    }

    private Future<Boolean> successPromise() {
        DefaultPromise<Boolean> promise = new DefaultPromise<>(GlobalEventExecutor.INSTANCE);
        promise.setSuccess(true);
        return promise;
    }
}
