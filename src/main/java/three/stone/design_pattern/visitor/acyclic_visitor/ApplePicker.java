package three.stone.design_pattern.visitor.acyclic_visitor;

import java.util.ArrayList;
import java.util.List;

public class ApplePicker implements AppleVisitor{
    List<Apple> apples = new ArrayList<>();
    @Override
    public void visit(Apple apple) {
        apples.add(apple);
    }

    public List<Apple> getApples() {
        return apples;
    }
}
