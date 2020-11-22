package BarracksWarsCommandsStrikeBack.models.units;

public class Horseman extends AbstractUnit {
    private static final int HORSE_HEALTH = 50;
    private static final int HORSE_DAMAGE = 10;


    public Horseman() {
        super(HORSE_HEALTH, HORSE_DAMAGE);
    }
}
