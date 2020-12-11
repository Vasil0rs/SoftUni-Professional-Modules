package CounterStriker.models.guns;

public class Pistol extends GunImpl {
    
 private static final int BULLET_TO_SHOOT = 1;
    
    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
         if (super.getBulletsCount() < BULLET_TO_SHOOT) {
            return 0;
        }
        super.decreaseBullets(BULLET_TO_SHOOT);
        return BULLET_TO_SHOOT;
    }
}
