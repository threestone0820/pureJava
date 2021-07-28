package three.stone.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import three.stone.netty.RpcData;

import java.util.List;

public class JsonEncoder extends MessageToMessageEncoder<RpcData> {
    private static final JsonEncoder instance = new JsonEncoder();

    public static JsonEncoder getInstance() {
        return instance;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcData msg, List<Object> out)
            throws Exception {
        out.add(msg.toJsonSerializeData());
    }
}
