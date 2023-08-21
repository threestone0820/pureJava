package three.stone.design_pattern.decorator;

import three.stone.design_pattern.adapter.Shape;

public class LineColorDecorator extends ShapeDecorator {
    public LineColorDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        System.out.println("Fill line color.");
    }

    @Override
    public void resize() {
        shape.draw();
    }

    @Override
    public String description() {
        String description = shape.description();
        return description + " with line color.";
    }
}
