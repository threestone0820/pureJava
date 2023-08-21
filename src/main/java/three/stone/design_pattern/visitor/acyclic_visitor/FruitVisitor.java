package three.stone.design_pattern.visitor.acyclic_visitor;

import java.util.List;

public interface FruitVisitor {



    static List<Apple> pickApples(List<Fruit> fruits) {
        ApplePicker applePicker = new ApplePicker();
        fruits.forEach(fruit -> fruit.accept(applePicker));
        return applePicker.getApples();
    }

    static int calculateGrapeWeight(List<Fruit> fruits) {
        GrapeWeightCalculator grapeWeightCalculator = new GrapeWeightCalculator();
        fruits.forEach(fruit -> fruit.accept(grapeWeightCalculator));
        return grapeWeightCalculator.getWeight();
    }

    static int numberOfFruits(List<Fruit> fruits) {
        CountVisitor visitor = new CountVisitor();
        fruits.forEach(fruit -> fruit.accept(visitor));
        return visitor.getNumberOfFruit();
    }
}
