package three.stone.codec;

import java.util.HashMap;
import java.util.Map;

public interface Codec<F, T> {
    T encode(F from);

    F decode(T to);

    Map<Class<?>, Codec> instances = new HashMap<>();
    static <C> C getInstance(Class<C> clazz) {
        Codec<?, ?> instance = instances.get(clazz);
        if (instance == null) {
            try {
                instance = (Codec<?, ?>)clazz.newInstance();
                instances.put(clazz, instance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (C)instance;
    }

}
