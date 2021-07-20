package three.stone.base;

public class VarHolder <T>{
    private T value;

    public VarHolder() {
        this.value = null;
    }

    public VarHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
