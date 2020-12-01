package CounterStriker.models.guns;

public class Pistol extends GunImpl {

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int bullets = this.getBulletsCount() - 1;
        if (bullets == 0) {
            return 0;
        }
        return bullets;
    }
}
