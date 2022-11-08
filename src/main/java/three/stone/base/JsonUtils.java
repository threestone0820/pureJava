package three.stone.base;

import com.fasterxml.jackson.core.JsonGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import three.stone.exception.Exceptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonUtils {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Return a double given an object that was stored in json and is
     * supposed to be a number.
     *
     * @param o the object to convert to double
     * @return a double value
     * @throws NullPointerException if o is null
     * @throws RuntimeException if o can not be converted to double
     */
    public static double asDouble(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o instanceof Long) {
            return ((long) o);
        } else if (o instanceof Double) {
            return (double) o;
        } else {
            throw new RuntimeException("Could not convert " + o + " to double");
        }
    }

    /**
     * Return an integer given an object that was stored in json and supposed
     * to be an integer.
     * @param o the object extracted from json
     * @return an integer
     * @throws NullPointerException if o is null
     * @throws RuntimeException if o can not be converted to int
     */
    public static int asInt(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o instanceof Long) {
            return (int) ((long) o);
        } else if (o instanceof Integer) {
            return (int) o;
        } else {
            throw new RuntimeException("Could not convert " + o + " to int");
        }
    }

    /**
     * We use org.json to stringify objects, because it doesn't block for
     * 10-60s for no good reason. We have this copy pasted code (wrap and
     * mapToJSON), because org.json treats null values as undefined.
     *
     * @param map
     * @return
     */
    public static String toJSONString(Map<String, Object> map) {
        return mapToJSON(map).toString();
    }

    public static String toJSONString(Collection<Object> list) {
        return arrToJSON(list).toString();
    }

    /**
     * Use this with plain objects like Strings, Integer, Long, Double, Boolean, null
     * @param obj the object to encode
     * @return json string representation of obj
     */
    public static String toJSONString(Object obj) {
        return JSONObject.valueToString(obj);
    }

    /**
     * Copied from org.json.JSONObject.wrap, that treats Maps a bit specially
     * Also if object is JsonEncodable, uses that instead.
     * (check comment).
     */
    private static Object wrap(Object object) {
        try {
            if (object == null) {
                return JSONObject.NULL;
            }
            if (object instanceof JsonEncodable<?>) {
                return wrap(((JsonEncodable<?>) object).toJson());
            }
            if (object instanceof JSONObject || object instanceof JSONArray
                    || JSONObject.NULL.equals(object) || object instanceof JSONString
                    || object instanceof Byte || object instanceof Character
                    || object instanceof Short || object instanceof Integer
                    || object instanceof Long || object instanceof Boolean
                    || object instanceof Float || object instanceof Double
                    || object instanceof String) {
                return object;
            }

            if (object instanceof Collection) {
                return arrToJSON((Collection<Object>) object);
            }
            if (object.getClass().isArray()) {
                return arrToJSON((Object []) object);
            }
            if (object instanceof Map) {
                // Change to use our Map->JSONObject
                return mapToJSON((Map<String, Object>) object);
            }
            Package objectPackage = object.getClass().getPackage();
            String objectPackageName = objectPackage != null ? objectPackage
                    .getName() : "";
            if (objectPackageName.startsWith("java.")
                    || objectPackageName.startsWith("javax.")
                    || object.getClass().getClassLoader() == null) {
                return object.toString();
            }
            return new JSONObject(object);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Copied from org.json.JSONObject.JSONObject(Map), that puts
     * json nulls instead of skipping them
     */
    public static JSONObject mapToJSON(Map<String, Object> map) {
        JSONObject res = new JSONObject();
        Iterator<Entry<String, Object>> i = map.entrySet().iterator();
        while (i.hasNext()) {
            Entry<String, Object> entry = i.next();
            Object value = entry.getValue();
            res.put(entry.getKey(), wrap(value));
        }
        return res;
    }

    public static void extendJSON(JSONObject data, Map<String, Object> map) {
        Iterator<Entry<String, Object>> i = map.entrySet().iterator();
        while (i.hasNext()) {
            Entry<String, Object> entry = i.next();
            Object value = entry.getValue();
            data.put(entry.getKey(), wrap(value));
        }
    }

    public static void extendJSON(JSONObject data, String key, Object value) {
        data.put(key, wrap(value));
    }

    public static JSONArray arrToJSON(Collection<? extends Object> collection) {
        JSONArray res = new JSONArray();
        Iterator<? extends Object> iter = collection.iterator();
        while (iter.hasNext()) {
            res.put(wrap(iter.next()));
        }
        return res;
    }

    public static JSONArray arrToJSON(Object [] arr) {
        JSONArray res = new JSONArray();
        for (int i = 0; i < arr.length; ++i) {
            res.put(wrap(arr[i]));
        }
        return res;
    }

    /**
     * We use org.json.simple for parsing, because it returns plain Java types (Maps, Lists).
     * @param str
     * @return plain Java Types
     */
    public static Object parseJson(String str) {
        try {
            return JsonStreamingUtils.parseAny(JsonStreamingUtils.createParser(str));
        } catch (IOException e) {
            logger.error("failed to parse json: {}", Exceptions.toString(e));
            return null;
        }
    }

    public static String toUnshortenedJsonStr(Object report) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            JsonGenerator generator = JsonStreamingUtils.createGenerator(out);
            JsonStreamingUtils.writeAny(generator, report);
            generator.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to encode report!", e);
        }
        return out.toString();
    }

    public static Map<String, String> JSONObjectToMap(org.json.simple.JSONObject obj) {
        Map<String, String> res = new HashMap<String, String>();
        Set keys = obj.keySet();
        Iterator a = keys.iterator();

        while(a.hasNext()) {
            String key = (String)a.next();
            String value = (String)obj.get(key);
            res.put(key, value);
        }
        return res;
    }

    public static Map<String, String> JSONObjectToMap(JSONObject obj) {
        Map<String, String> res = new HashMap<String, String>();
        Set keys = obj.keySet();
        Iterator a = keys.iterator();

        while(a.hasNext()) {
            String key = String.valueOf(a.next());
            String value = String.valueOf(obj.get(key));
            res.put(key, value);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(imohashMod("bg.mrdh56nbshr9unzj", 128));
        System.out.println(imohashMod2("bg.mrdh56nbshr9unzj", 128));
    }

    public static int imohashMod(String s, int b) {
        return mod(hash(s), b);
    }

    public static int imohashMod2(String s, int b) {
        return mod(imohash(s), b);
    }

    public static int mod(long a, int b) {
        int res = (int) (a % b);
        res = res < 0 ? res + b : res;
        return res;
    }

    public static long hash(String s) {
        if (s == null) {
            return 587058L;
        }
        if (s.isEmpty()) {
            return 0L;
        }

        long res = s.charAt(0) << 7;
        for (int i = 0; i < s.length(); ++i) {
            res = (1000003 * res) ^ s.charAt(i);
        }
        res ^= s.length();
        if (res == -1L) {
            res = -2L;
        }
        return res;
    }

    public static long imohash(String key) {
        long h = 0;
        key = key != null ? key : "";

        for (char x : key.toCharArray()) {
            h = ((x + h) & 0xFFFFFFFFL);
            h = ((h << 10) + h) & 0xFFFFFFFFL;
            h ^= (h >>> 6);
        }
        h = ((h << 3) + h) & 0xFFFFFFFFL;
        h ^= (h >>> 11);
        h = ((h << 15) + h) & 0xFFFFFFFFL;
        return h;
    }
}
