package three.stone.base;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static Map<String, Object> construct(Object... keysAndValues) {
        Map<String, Object> data = new HashMap<>();
        int n = keysAndValues.length;
        if (n % 2 != 0) {
            throw new IllegalArgumentException("need even number of arguments (keys/values)");
        }
        for (int i = 0; i < n; i += 2) {
            data.put((String) keysAndValues[i], keysAndValues[i + 1]);
        }
        return data;
    }
}
