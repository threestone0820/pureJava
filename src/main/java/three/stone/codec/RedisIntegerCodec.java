package three.stone.codec;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class RedisIntegerCodec extends RedisCodec<Integer>{
    @Override
    public String encode(Integer from) {
        Objects.requireNonNull(from);
        return String.valueOf(from);
    }

    @Override
    public Integer decode(String to) {
        return StringUtils.isEmpty(to) ? null : Integer.parseInt(to);
    }

    public static RedisIntegerCodec getInstance() {
        return Codec.getInstance(RedisIntegerCodec.class);
    }
}
