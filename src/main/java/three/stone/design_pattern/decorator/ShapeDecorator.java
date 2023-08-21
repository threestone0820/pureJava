package three.stone.design_pattern.decorator;

import three.stone.design_pattern.adapter.Shape;

public abstract class ShapeDecorator implements Shape {
    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }
}
