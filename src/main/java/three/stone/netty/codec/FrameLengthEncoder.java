package three.stone.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FrameLengthEncoder extends MessageToByteEncoder<byte[]> {
    private static final FrameLengthEncoder instance = new FrameLengthEncoder();

    public static FrameLengthEncoder getInstance() {
        return instance;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out)
            throws Exception {
        out.writeInt(msg.length);
        out.writeBytes(msg);
    }
}
