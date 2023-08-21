package three.stone.design_pattern.creational.abstract_factory;

public class Kingdom {
    private King king;
    private Castle castle;
    private Army army;

    public Kingdom(King king, Castle castle, Army army) {
        this.king = king;
        this.castle = castle;
        this.army = army;
    }

    @Override
    public String toString() {
        return "Kingdom{" +
                "king=" + king.getDescription() +
                ", castle=" + castle.getDescription() +
                ", army=" + army.getDescription() +
                '}';
    }
}
