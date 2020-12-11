package easterRaces.entities.racers;

import easterRaces.entities.drivers.Driver;

import java.util.Collection;
import java.util.HashMap;

import static easterRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private HashMap<String, Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new HashMap<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS, 1));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }
    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers.values();
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        }

        if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException
                    (String.format(DRIVER_NOT_PARTICIPATE, driver.getName()));
        }

        if (drivers.containsKey(driver.getName())) {
            throw new IllegalArgumentException
                    (String.format(DRIVER_ALREADY_ADDED, driver.getName(), this.name));
        }

        drivers.put(driver.getName(), driver);
    }
}
