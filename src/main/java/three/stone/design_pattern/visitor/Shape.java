package three.stone.design_pattern.visitor;

interface Shape {
    String draw();

    /**
     * Declare the element interface. If you’re working with an existing element class hierarchy,
     * add the abstract “accept” method to the base class of the hierarchy.
     * This method should accept a visitor object as an argument.
     */
    void accept(ShapeVisitor shapeVisitor);
}
