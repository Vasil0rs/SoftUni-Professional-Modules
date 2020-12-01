package CounterStriker.models.guns;

public class Rifle extends GunImpl {


    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int bullets = this.getBulletsCount() - 10;
        if (bullets == 0) {
            return 0;
        }
        return bullets;
    }
}
