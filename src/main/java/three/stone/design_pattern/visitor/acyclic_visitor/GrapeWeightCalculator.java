package three.stone.design_pattern.visitor.acyclic_visitor;

public class GrapeWeightCalculator implements GrapeVisitor{
    private int weight = 0;

    public int getWeight() {
        return weight;
    }

    @Override
    public void visit(Grape grape) {
        weight += grape.getWeight();
    }
}
