package easterRaces.entities.cars;

public class SportsCar extends BaseCar{
    private static final double CUBIC_CENTIMETER = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETER);
    }
}
