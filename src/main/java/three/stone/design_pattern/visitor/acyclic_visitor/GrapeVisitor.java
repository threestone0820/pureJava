package three.stone.design_pattern.visitor.acyclic_visitor;

public interface GrapeVisitor extends FruitVisitor{
    void visit(Grape grape);
}
