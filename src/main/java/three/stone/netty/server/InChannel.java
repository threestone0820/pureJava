package three.stone.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;

public class InChannel extends ChannelInboundHandlerAdapter {

    private BaseHandler handler;
    private String ownName;
    private int ownShard;
    private String name;
    private int shard;
    private SocketChannel channel;
    private Map<Long, RpcContext> incomingRpcs;

    public InChannel(BaseHandler handler, String ownName, int ownShard, SocketChannel channel) {
        this.handler = handler;
        this.ownName = ownName;
        this.ownShard = ownShard;
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof Map)) {
            throw new RuntimeException("got a message that is not a map " + msg);
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public String toString() {
        return name + "." + shard;
    }
}
