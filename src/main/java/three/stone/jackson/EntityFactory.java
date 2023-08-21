package three.stone.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityFactory {
    private static ObjectMapper instance = null;

    public static ObjectMapper getObjectMapper() {
        if (instance == null) {
            instance = new ObjectMapper();
            instance.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            instance.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        }
        return instance;
    }

    static Map<Class<?>, List<String>> nonClientFieldsMap = new HashMap<>();
    static Map<Class<?>, List<String>> nonDbFieldsMap = new HashMap<>();
    static Set<Class<?>> registered = new HashSet<>();

    static void register(Class<?> clazz) {
        if (registered.contains(clazz)) {
            return;
        }
        List<Field> fields = FieldUtils.getAllFieldsList(clazz);
        List<String> nonClientFields = new ArrayList<>();
        List<String> nonDbFields = new ArrayList<>();
        for (Field field : fields) {
            JsonProperty jp = field.getAnnotation(JsonProperty.class);
            if (null != jp) {
                IgnoreDB nonDb = field.getAnnotation(IgnoreDB.class);
                if (nonDb != null) {
                    nonDbFields.add(jp.value());
                }
                IgnoreClient nonClient = field.getAnnotation(IgnoreClient.class);
                if (nonClient != null) {
                    nonClientFields.add(jp.value());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(nonDbFields)) {
            nonDbFieldsMap.put(clazz, nonDbFields);
        }
        if (CollectionUtils.isNotEmpty(nonClientFields)) {
            nonClientFieldsMap.put(clazz, nonClientFields);
        }
        registered.add(clazz);
    }

    public static boolean needClient(Class<?> clazz, String field) {
        register(clazz);
        return !nonClientFieldsMap.get(clazz).contains(field);
    }

    public static boolean needDb(Class<?> clazz, String field) {
        register(clazz);
        return !nonDbFieldsMap.get(clazz).contains(field);
    }

}
