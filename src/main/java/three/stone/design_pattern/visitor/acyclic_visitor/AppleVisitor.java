package three.stone.design_pattern.visitor.acyclic_visitor;

public interface AppleVisitor extends FruitVisitor{
    void visit(Apple apple);
}
