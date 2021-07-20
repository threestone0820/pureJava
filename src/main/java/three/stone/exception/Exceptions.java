package three.stone.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Exceptions {
    /**
     * return full stack trace, avoid truncates exceptions
     */
    public static String toString(Throwable e) {
        if (null == e) {
            return "";
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(outputStream));
        return outputStream.toString();
    }

    public static Throwable safeGetCause(Throwable e) {
        Throwable cause = e.getCause();
        return cause == null ? e : cause;
    }
}
