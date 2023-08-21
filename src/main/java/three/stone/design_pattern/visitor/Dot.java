package three.stone.design_pattern.visitor;

class Dot implements Shape{
    @Override
    public String draw() {
        return null;
    }

    @Override
    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }
}
