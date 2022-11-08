package three.stone.exception;

public class ReturnException extends RuntimeException{
    private Object value;

    public ReturnException(Object value) {
        super(null, null, true, false);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
