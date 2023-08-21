package three.stone.design_pattern.visitor;

public class Circle implements Shape{
    @Override
    public String draw() {
        return null;
    }

    @Override
    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }
}
