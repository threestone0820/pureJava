package three.stone.design_pattern.decorator;

import three.stone.design_pattern.adapter.Circle;
import three.stone.design_pattern.adapter.Shape;

public class Main {
    public static void main(String[] args) {
        Shape shape = new FillColorDecorator(new LineColorDecorator(new Circle()));
        shape.draw();
    }
}
