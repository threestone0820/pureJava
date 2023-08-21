package three.stone.design_pattern.visitor.acyclic_visitor;

import java.util.Arrays;
import java.util.List;

/**
 * 相比Visitor，acyclic visitor的优势是：
 * Adding a new fruit to the hierarchy won't impact all the visitors,
 * only the ones that are interested in the new fruit:
 */
public class App {
    public static void main(String[] args) {
        List<Fruit> fruits = Arrays.asList(
                new Apple("apple1"),
                new Grape(1),
                new Apple("apple2"),
                new Grape(1024));
        System.out.println(FruitVisitor.pickApples(fruits));
        System.out.println(FruitVisitor.calculateGrapeWeight(fruits));
        System.out.println(FruitVisitor.numberOfFruits(fruits));
    }
}
