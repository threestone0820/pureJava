package three.stone.effective;

public abstract class Foo<T extends Foo<T>> {
    abstract T deepCopy();
}
