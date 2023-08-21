package three.stone.design_pattern.visitor;

import java.util.List;

/**
 * The signature of a visiting
 * method lets the visitor identify the exact class of the
 * element that it's dealing with.
 */
interface ShapeVisitor {
    void visit(Dot dot);

    void visit(Circle circle);

    /**
     * Extension methods can be used to make the pattern look more transparent to users
     */
    static void xmlExport(List<Shape> shapes) {
        ShapeVisitor visitor = new XMLExportShapeVisitor();
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }

    static void jsonExport(List<Shape> shapes) {
        ShapeVisitor visitor = new JsonExportShapeVisitor();
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
}
