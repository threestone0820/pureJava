package three.stone.design_pattern.decorator;

import three.stone.design_pattern.adapter.Shape;

public class FillColorDecorator extends ShapeDecorator{
    public FillColorDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        System.out.println("fill some color to Shape.");
    }

    @Override
    public void resize() {
        shape.resize();
    }

    @Override
    public String description() {
        String description = shape.description();
        return description + " with color";
    }
}
