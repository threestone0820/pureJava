package three.stone.basis;

public class B extends A{
    private int age;

    public B(int age, String name) {
        super(name);
        this.age = age;
    }


}

class C extends B {
    private long i;

    public C(int age, String name, long i) {
        super(age, name);
        this.i = i;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        C c = new C(100, "beijing", 200);
        C c2 = (C) c.clone();
        System.out.println();
    }
}
