package three.stone.basis;

public class A implements Cloneable{
    private String name;

    public A(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
