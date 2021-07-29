package three.stone.base;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;

public class EventLoop {
    private static final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(
            1, new DefaultThreadFactory("Main", Thread.MAX_PRIORITY));

    public static EventLoopGroup getEventLoopGroup() {
        return eventLoopGroup;
    }

    public static Class<?> getEventLoopGroupClass() {
        return NioEventLoopGroup.class;
    }

}
