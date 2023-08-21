package three.stone.design_pattern.adapter;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    List<Shape> shapes = new ArrayList<Shape>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void draw() {
        shapes.forEach(Shape::draw);
    }

    public void resize() {
        shapes.forEach(Shape::resize);
    }
}
