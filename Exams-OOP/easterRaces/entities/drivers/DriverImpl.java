package easterRaces.entities.drivers;

import easterRaces.common.ExceptionMessages;
import easterRaces.entities.cars.Car;

import static easterRaces.common.ExceptionMessages.CAR_INVALID;

public class DriverImpl implements Driver {
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.numberOfWins = 0;
        this.car = null;
        this.setCanParticipate(false);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    private void setCanParticipate(boolean canParticipate) {
        this.canParticipate = canParticipate;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public boolean getCanParticipate() {
        return this.car != null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException(CAR_INVALID);
        }
        this.car = car;
        this.setCanParticipate(true);
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }
}
