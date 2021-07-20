package three.stone.exception;

import java.util.concurrent.CompletionException;

/**
 * This is used to throw exceptions from inside a CompletionStage listener.
 *
 * The problem is that if you want to handle some exceptions in a completion
 * stage, but not others, if the exception is a checked one, you're not
 * allowed to throw it. So in those cases wrap it with this class.
 */
public class WrapNonRuntimeException extends RuntimeException {

    public WrapNonRuntimeException(Throwable e) {
        super(e.toString(), e.getCause(), true, false);
    }

    public static RuntimeException wrap(Throwable e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }

        return new WrapNonRuntimeException(e);
    }

    public static Throwable unWrap(Throwable e) {
        while (e instanceof WrapNonRuntimeException ||
                e instanceof CompletionException) {
            e = e.getCause();
        }

        return e;
    }
}
