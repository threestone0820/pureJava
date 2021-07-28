package three.stone.base;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public interface JacksonSerializable {
    public void jacksonSerialize(JsonGenerator generator) throws IOException;
}
