package three.stone.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import three.stone.base.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonDecoder extends MessageToMessageDecoder<byte[]> {
    private static final JsonDecoder instance = new JsonDecoder();

    public static JsonDecoder getInstance() {
        return instance;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out)
            throws Exception {
        String msgStr = new String(msg, StandardCharsets.UTF_8);
        Object obj = JsonUtils.parseJson(msgStr);
        if (obj != null) {
            out.add(obj);
        } else {
            throw new RuntimeException("got malformed json on channel: " +
                    msgStr.substring(0, 100));
        }
        out.add(JsonUtils.parseJson(new String(msg, StandardCharsets.UTF_8)));
    }
}
