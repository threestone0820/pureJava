package three.stone.design_pattern.visitor.acyclic_visitor;

public class CountVisitor implements AppleVisitor, GrapeVisitor{
    private int numberOfFruit = 0;
    @Override
    public void visit(Apple apple) {
        this.numberOfFruit += 1;
    }

    @Override
    public void visit(Grape grape) {
        this.numberOfFruit += 1;
    }

    public int getNumberOfFruit() {
        return numberOfFruit;
    }
}
