package three.stone.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static three.stone.jackson.NormalResponse.MessageType.BIG_GROUP;
import static three.stone.jackson.NormalResponse.MessageType.IM;
import static three.stone.jackson.Push.PushType.APNS;
import static three.stone.jackson.Push.PushType.GCM;

public class JacksonAnnotationTest {
    @Test
    public void whenSerializingUsingJsonAnyGetterThenCorrect() throws IOException {
        ExtendableBean myBean = new ExtendableBean("My bean");
        myBean.addProperty("key1", "val1");
        myBean.addProperty("key2", "val2");

        /**
         * 如果有 @JsonAnyGetter
         * 输出：{"name":"My bean","key1":"val1","key2":"val2"}，
         *
         * 另外此时不要在变量上增加@JsonProperty注解，加上的话输出是：
         * {"name":"My bean","properties":{"key1":"val1","key2":"val2"},"key1":"val1","key2":"val2"}
         * （@JsonAnyGetter 和 @JsonProperty 重复了？）
         *
         * 如果没有@JsonAnyGetter注解
         * 输出：{"name":"My bean","properties":{"key1":"val1","key2":"val2"}}
         */
        String result = new ObjectMapper().writeValueAsString(myBean);
        System.out.println(result);


        ExtendableBean obj = new ObjectMapper()
                .readerFor(ExtendableBean.class)
                .readValue(result);
        System.out.println(obj.getProperties());
    }

    @Test
    public  void testJsonProperty() throws Exception {
        String response = "{\"code\":400,\"message\":\"success\", \"key1\":\"value1\",\"key2\":\"value2\",\"known_key\":\"known_value\"}";
        DeleteResponse deleteResponse =  new ObjectMapper()
                .readerFor(DeleteResponse.class)
                .readValue(response);
        System.out.println(deleteResponse);

        String json = new ObjectMapper().writeValueAsString(deleteResponse);
        System.out.println(json);
    }

    @Test
    public void testSomeAnnotationAndDifferentJavaType() throws Exception {

        NormalResponse response = getInstance();
        String json = new ObjectMapper().writeValueAsString(response);
        System.out.println(json);
        NormalResponse response1 = new ObjectMapper()
                .readerFor(NormalResponse.class)
                .readValue(json);
        System.out.println();
    }

    @Test
    public void testReadValueWithObjectMapper() throws Exception {
        NormalResponse instance = getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String jsonString = objectMapper.writeValueAsString(instance);
        // json 字符串 -> object
        NormalResponse response = objectMapper.readValue(jsonString, NormalResponse.class);

        // json 字符串 -> JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        // json 字符串 -> 对象列表类型
        String pushesString = jsonNode.get("pushes").toString();
        List<Push> pushes = objectMapper.readValue(pushesString, new TypeReference<List<Push>>() {});

        // json 字符串 -> Map<String, Object> 类型
        Map<String, Object> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });
        System.out.println();

    }

    private NormalResponse getInstance() {
        List<String> ids = new ArrayList<>(Arrays.asList("1", "2"));
        Set<String> names = new HashSet<>(Arrays.asList("Beijing", "Shanghai"));
        Map<String, Object> properties = new HashMap<>(ImmutableMap.of(
                "key1", 1,
                "key2", "val2",
                "key3", new HashSet<>(Arrays.asList(1, 2, 3)),
                "key4", IM));

        return new NormalResponse(
                400, null,
                true, ids,
                names, properties,
                Arrays.asList(BIG_GROUP, IM),
                new Push("push_type", "push_name", APNS),
                Arrays.asList(new Push("push1", "push_name1", GCM), new Push("push2", "push_name2", APNS)));
    }
}
