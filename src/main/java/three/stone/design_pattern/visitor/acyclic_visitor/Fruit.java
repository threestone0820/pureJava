package three.stone.design_pattern.visitor.acyclic_visitor;

public abstract class Fruit {
    abstract void accept(FruitVisitor visitor);
}
