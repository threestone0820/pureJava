package three.stone.design_pattern.creational.abstract_factory;

/**
 * The system should be configured with one of the multiple families of products
 *
 * The family of related product objects is designed to be used together, and you need to enforce this constraint
 */
public class App {
    public static void main(String[] args) {
        System.out.println(KingdomFactory.createKing(KingdomFactory.KingdomType.ELF));
        System.out.println(KingdomFactory.createKing(KingdomFactory.KingdomType.ORC));
    }
}
