package three.stone.design_pattern.visitor;

import java.util.Arrays;
import java.util.List;

/**
 * Use the Visitor pattern when
 *
 * An object structure contains many classes of objects with differing interfaces,
 * and you want to perform operations on these objects that depend on their concrete classes.
 *
 * Many distinct and unrelated operations need to be performed on objects in an object structure,
 * and you want to avoid "polluting" their classes with these operations.
 * Visitor lets you keep related operations together by defining them in one class.
 * When the object structure is shared by many applications,
 * use Visitor to put operations in just those applications that need them.
 *
 * The classes defining the object structure rarely change, but you often want to define new operations over the structure.
 * Changing the object structure classes requires redefining the interface to all visitors, which is potentially costly.
 * If the object structure classes change often, then it's probably better to define the operations in those classes.
 *
 *
 * 缺点：
 * It assumes that the hierarchy of visited classes doesn't change. If it changes,
 * the visitor interface and all existing visitors must be updated to accomodate the change.
 *
 * There's a cyclic dependency between the classes in the hierarchy and the methods in the visitor.
 */
public class App {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(new Dot(), new Circle());

        ShapeVisitor.xmlExport(shapes);
        ShapeVisitor.jsonExport(shapes);
    }
}
