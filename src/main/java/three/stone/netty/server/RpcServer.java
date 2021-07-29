package three.stone.netty.server;

import com.sun.security.ntlm.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.base.EventLoop;
import three.stone.base.Stoppable;
import three.stone.netty.codec.FrameLengthDecoder;
import three.stone.netty.codec.FrameLengthEncoder;
import three.stone.netty.codec.JsonDecoder;
import three.stone.netty.codec.JsonEncoder;

import java.util.Set;

public class RpcServer implements Stoppable {
    private static Logger logger = LogManager.getLogger();

    private BaseHandler handler;
    private Set<InChannel> incomingChannels;
    private String name;
    private int shard;
    private int port;
    private ChannelFuture channelFuture;
    private Channel serverChannel;

    public RpcServer(BaseHandler handler, String name, int shard, int port) {
        this.handler = handler;
        this.name = name;
        this.shard = shard;
        this.port = port;
    }

    public void start() {
        ServerBootstrap server = new ServerBootstrap();
        server.group(EventLoop.getEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        InChannel inChannel = new InChannel(handler, name, shard, channel);
                        incomingChannels.add(inChannel);

                        channel.pipeline().addLast(new FrameLengthDecoder());
                        channel.pipeline().addLast(JsonDecoder.getInstance());
                        channel.pipeline().addLast(inChannel);

                        channel.pipeline().addLast(FrameLengthEncoder.getInstance());
                        channel.pipeline().addLast(JsonEncoder.getInstance());
                        channel.closeFuture().addListener((ChannelFutureListener) channelFuture -> {
                            logger.info("Removing inChannel {}", inChannel);
                            incomingChannels.remove(inChannel);
                        });
                    }
                });
        channelFuture = server.bind(port);
        serverChannel = channelFuture.channel();

    }

    @Override
    public void stop() {
        serverChannel.close();
    }
}
