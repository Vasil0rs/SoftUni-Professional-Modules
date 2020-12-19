package viceCity.models.guns;

public class Pistol extends BaseGun {

    private final static int BULLETS_IN_BARREL = 10;
    private final static int TOTAL_BULLETS = 100;
    private final static int BULLETS_FIRE_PISTOL = 1;

    public Pistol(String name) {
        super(name, BULLETS_IN_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (this.getBulletsPerBarrel() == 0) {
            this.reload();
            if (this.getBulletsPerBarrel() == 0) {
                return 0;
            }
        }
        this.setBulletsPerBarrel(this.getBulletsPerBarrel() - BULLETS_FIRE_PISTOL);
        return BULLETS_FIRE_PISTOL;
    }

    private void reload() {
        if (this.getTotalBullets() > 0) {
            int restTotalBullets = this.getTotalBullets() - BULLETS_IN_BARREL;
            this.setTotalBullets(restTotalBullets);
            this.setBulletsPerBarrel(BULLETS_IN_BARREL);
        }
    }
}
