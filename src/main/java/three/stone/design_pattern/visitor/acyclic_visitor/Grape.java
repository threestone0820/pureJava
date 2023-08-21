package three.stone.design_pattern.visitor.acyclic_visitor;

public class Grape extends Fruit{
    private int weight;

    public Grape(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    void accept(FruitVisitor visitor) {
        if (GrapeVisitor.class.isAssignableFrom(visitor.getClass())) {
            ((GrapeVisitor)visitor).visit(this);
        }
    }
}
