package three.stone.base;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;

public class EventLoop {
    private static EventLoopGroup eventLoopGroup =  new KQueueEventLoopGroup(
            1, new DefaultThreadFactory("Main", Thread.MAX_PRIORITY));

    public static EventLoopGroup getEventLoopGroup() {
        return eventLoopGroup;
    }

    public static Class<?> getEventLoopGroupClass() {
        return KQueueEventLoopGroup.class;
    }

    public static void main(String[] args) throws RuntimeException{
        int[] nums = new int[]{1, 2, 3};
        int i = 0;
        while (i <= 3) {
            try {
                System.out.println(nums[i]);
                i++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }
    }

}
