package three.stone.design_pattern.adapter;

public class GeometricShapeAdapter implements Shape{
    private GeometricShape geometricShape;

    public GeometricShapeAdapter(GeometricShape geometricShape) {
        this.geometricShape = geometricShape;
    }

    @Override
    public void draw() {
        geometricShape.drawShape();
    }

    @Override
    public void resize() {
        System.out.printf("geometric shape cannot resize.");
    }

    @Override
    public String description() {
        return geometricShape.getClass().getSimpleName();
    }
}
