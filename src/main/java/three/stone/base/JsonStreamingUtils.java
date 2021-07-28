package three.stone.base;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.exception.Exceptions;
import three.stone.exception.WrapNonRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonStreamingUtils {

    private static final Logger logger = LogManager.getLogger();

    private static JsonFactory jsonFactory = new JsonFactory();

    public static JsonParser createParser(InputStream inStream) throws IOException {
        return jsonFactory.createParser(inStream);
    }

    public static JsonParser createParser(String jsonStr) throws IOException {
        return jsonFactory.createParser(jsonStr);
    }

    public static JsonGenerator createGenerator(Writer writer) throws IOException {
        return jsonFactory.createGenerator(writer);
    }

    public static JsonGenerator createGenerator(OutputStream out) throws IOException {
        return jsonFactory.createGenerator(out);
    }

    public static Object parseAny(JsonParser parser) throws IOException {
        return parseAny(parser, parser.nextToken());
    }

    public static Object parseAny(JsonParser parser, JsonToken currentToken)
            throws IOException {
        Object value;
        switch (currentToken) {
            case START_OBJECT:
                value = parseMap(parser, false);
                break;
            case START_ARRAY:
                value = parseArray(parser, false);
                break;
            case VALUE_STRING:
                value = parser.getText();
                break;
            case VALUE_NUMBER_INT:
                value = parser.getLongValue();
                break;
            case VALUE_NUMBER_FLOAT:
                value = parser.getDoubleValue();
                break;
            case VALUE_TRUE:
                value = true;
                break;
            case VALUE_FALSE:
                value = false;
                break;
            case VALUE_NULL:
                value = null;
                break;
            default:
                throw new IOException("unexpected token");
        }

        return value;
    }

    public static Map<String, Object> parseMap(JsonParser parser)
            throws IOException {
        return parseMap(parser, true);
    }

    public static Map<String, Object> parseMap(
            JsonParser parser, boolean readStart) throws IOException {
        if ((readStart ? parser.nextToken() : parser.getCurrentToken()) !=
                JsonToken.START_OBJECT) {
            throw new IOException("expected start object");
        }

        Map<String, Object> res = new HashMap<>();
        while (parser.nextToken() == JsonToken.FIELD_NAME) {
            String key = parser.getCurrentName().intern();
            Object value = parseAny(parser, parser.nextToken());
            res.put(key, value);
        }

        if (parser.getCurrentToken() != JsonToken.END_OBJECT) {
            throw new IOException("expected end object");
        }
        return res;
    }

    public static List<Object> parseArray(JsonParser parser)
            throws IOException {
        return parseArray(parser, true);
    }

    public static List<Object> parseArray(
            JsonParser parser, boolean readStart) throws IOException {
        if ((readStart ? parser.nextToken() : parser.getCurrentToken()) !=
                JsonToken.START_ARRAY) {
            throw new IOException("expected start array");
        }

        List<Object> res = new ArrayList<>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            res.add(parseAny(parser, parser.getCurrentToken()));
        }
        return res;
    }

    public static void writeAny(JsonGenerator generator, Object value)
            throws IOException {
        if (value instanceof String) {
            generator.writeString((String) value);
        } else if (value instanceof Long) {
            generator.writeNumber((Long) value);
        } else if (value instanceof Map) {
            writeMap(generator, (Map<String, Object>) value);
        } else if (value instanceof Double) {
            generator.writeNumber((Double) value);
        } else if (value instanceof Boolean) {
            generator.writeBoolean((Boolean) value);
        } else if (value instanceof Iterable) {
            writeArray(generator,
                    (Iterable<? extends JacksonSerializable>) value);
        } else if (value == null) {
            generator.writeNull();
        } else if (value instanceof JacksonSerializable) {
            ((JacksonSerializable) value).jacksonSerialize(generator);
        } else if (value instanceof Integer) {
            generator.writeNumber((Integer) value);
        } else if (value instanceof Float) {
            generator.writeNumber((Float) value);
        } else {
            throw new IOException("can not write " + value);
        }
    }

    public static void writeMap(JsonGenerator generator,
            Map<String, ? extends Object> map) throws IOException {
        writeMap(generator, map, false);
    }

    public static void writeMap(JsonGenerator generator,
            Map<String, ? extends Object> map, boolean skipFailed) throws IOException {
        if (map == null) {
            generator.writeNull();
            return;
        }
        generator.writeStartObject();
        try {
            map.forEach((k, v) -> {
                try {
                    generator.writeFieldName(k);
                    writeAny(generator, v);
                } catch (Throwable e) {
                    if (skipFailed) {
                        logger.warn("failed to encode k:{} v:{} : {}", k, v, Exceptions.toString(e));
                    } else {
                        throw WrapNonRuntimeException.wrap(e);
                    }
                }
            });
        } catch (WrapNonRuntimeException e) {
            throw (IOException) e.getCause();
        }
        generator.writeEndObject();
    }

    public static void writeMapField(JsonGenerator generator,
            String fieldName, Map<String, ? extends Object> map)
            throws IOException {
        generator.writeFieldName(fieldName);
        writeMap(generator, map);
    }

    public static void writeArray(JsonGenerator generator,
            Iterable<? extends Object> array) throws IOException {
        if (array == null) {
            generator.writeNull();
            return;
        }
        generator.writeStartArray();
        for (Object item : array) {
            writeAny(generator, item);
        }
        generator.writeEndArray();
    }

    public static void writeArrayField(JsonGenerator generator,
            String fieldName, Iterable<? extends Object> array)
            throws IOException {
        generator.writeFieldName(fieldName);
        writeArray(generator, array);
    }
}
