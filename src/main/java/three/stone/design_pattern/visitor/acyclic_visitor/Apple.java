package three.stone.design_pattern.visitor.acyclic_visitor;

public class Apple extends Fruit{
    private String name;

    public Apple(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    void accept(FruitVisitor visitor) {
        if (AppleVisitor.class.isAssignableFrom(visitor.getClass())) {
            ((AppleVisitor)visitor).visit(this);
        }
    }
}
