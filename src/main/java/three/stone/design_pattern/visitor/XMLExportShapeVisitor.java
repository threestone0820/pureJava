package three.stone.design_pattern.visitor;

public class XMLExportShapeVisitor implements ShapeVisitor {
    @Override
    public void visit(Dot dot) {
        System.out.println("visit dot to get xml.");
    }

    @Override
    public void visit(Circle circle) {
        System.out.println("visit circle to get xml");
    }
}
