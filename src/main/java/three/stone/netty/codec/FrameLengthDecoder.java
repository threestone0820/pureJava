package three.stone.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

enum State{
    READ_LENGTH, READ_CONTENT;
}

public class FrameLengthDecoder extends ReplayingDecoder<State> {
    private static final int FRAME_LIMIT = 500_000_000;
    int length;

    public FrameLengthDecoder() {
        super(State.READ_LENGTH);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
        switch (state()) {
            case READ_LENGTH:
                length = in.readInt();
                checkpoint(State.READ_CONTENT);
            case READ_CONTENT:
                if (length < FRAME_LIMIT) {
                    byte[] content = new byte[length];
                    in.readBytes(content);
                    checkpoint(State.READ_LENGTH);
                    out.add(content);
                } else {
                    // this will close the channel
                    throw new Error("Got too big frame: " + length);
                }
                break;
            default:
                throw new Error("Shouldn't reach here.");
        }
    }
}
