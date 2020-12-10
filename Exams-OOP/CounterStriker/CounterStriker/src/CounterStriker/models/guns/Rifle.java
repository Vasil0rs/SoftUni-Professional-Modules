package CounterStriker.models.guns;

public class Rifle extends GunImpl {
    
  private static final int BULLET_TO_SHOOT = 10;

    public Rifle(String name, int bulletsCount) {
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
