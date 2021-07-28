package three.stone.netty;

import three.stone.base.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RpcData {
    Map<String, Object> data;

    public byte[] toJsonSerializeData() {
        return JsonUtils.toJSONString(data).getBytes(StandardCharsets.UTF_8);
    }
}
