package three.stone.jackson;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class MapEntity {
    public Map<String, Object> toMap() {
        return EntityFactory.getObjectMapper().convertValue(this, Map.class);
    }

    public Map<String, Object> toMapSkipNullValue() {
        Map<String, Object> result = toMap();
        return result
                .entrySet()
                .stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        return EntityFactory.getObjectMapper().convertValue(map, clazz);
    }

    public Map<String, Object> toClient() {
        Map<String, Object> map = toMap();
        return map.entrySet()
                .stream()
                .filter(e -> EntityFactory.needClient(this.getClass(), e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Object> toRedis() {
        Map<String, Object> map = toMap();
        return map.entrySet()
                .stream()
                .filter(e -> EntityFactory.needDb(this.getClass(), e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
