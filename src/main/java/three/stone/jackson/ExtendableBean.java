package three.stone.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExtendableBean {
    private static Logger logger = LogManager.getLogger();
    @JsonProperty
    private String name;
    // 这里就不要加@JsonProperty，否则会多出一个properties的key，value为map
    private Map<String, Object> properties = new HashMap<>();

    // 反序列化，需要加这个注解
    @JsonCreator
    public ExtendableBean() {

    }

    public ExtendableBean(String name) {
        this.name = name;
    }

    public void addProperty(String key, Object value) {
        properties.put(key, value);
    }

    // 序列化后，properties中的key value会取出来，和name平级
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void printWarning(String key, Object value) {
        logger.warn("unknown field key:{} value:{}", key, value);
    }

    public static void main(String[] args) throws IOException {
        ExtendableBean bean = new ExtendableBean("test any getter");
        bean.addProperty("key1", "value1");
        bean.addProperty("key2", Arrays.asList(1, 2, 3));
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("after serialization: {}", objectMapper.writeValueAsString(bean));
        String json = "{\"name\":\"test any getter\",\"key1\":\"value1\",\"key2\":[1,2,3]}";
        ExtendableBean bean2 = objectMapper.readerFor(ExtendableBean.class).readValue(json);

    }

}
