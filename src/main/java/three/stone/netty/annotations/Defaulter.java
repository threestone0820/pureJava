package three.stone.netty.annotations;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.function.Supplier;

public class Defaulter {
    private Supplier<Object> supplier;

    public Defaulter(Annotation annotation) {
        this.supplier = makeSupplier(annotation);
        Objects.requireNonNull(supplier);
    }

    public Object getDefault() {
        return supplier.get();
    }

    public static boolean isDefaultAnnotation(Annotation a) {
        return makeSupplier(a) != null;
    }

    public static Supplier<Object> makeSupplier(Annotation annotation) {
        if (annotation instanceof DefaultNull ) {
            return () -> null;
        } else if (annotation instanceof DefaultInteger) {
            return ((DefaultInteger) annotation)::value;
        } else if (annotation instanceof DefaultLong) {
            return ((DefaultLong)annotation)::value;
        } else if (annotation instanceof DefaultBoolean) {
            return ((DefaultBoolean) annotation)::value;
        } else if (annotation instanceof DefaultString) {
            return ((DefaultString) annotation)::value;
        } else {
            return null;
        }
    }
}
