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
}
