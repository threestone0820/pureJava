package three.stone.design_pattern.creational.abstract_factory;

public interface KingdomFactory {
    Castle createCastle();

    King createKing();

    Army createArmy();

    static Kingdom createKing(KingdomType type) {
        KingdomFactory factory;
        switch (type) {
            case ELF:
                factory = new ElfKingdomFactory();
                break;
            case ORC:
                factory = new OrcKingdomFactory();
                break;
            default:
                throw new IllegalArgumentException("known type");

        }
        return new Kingdom(factory.createKing(), factory.createCastle(), factory.createArmy());
    }

    enum KingdomType{
        ELF, ORC
    }
}
