package easterRaces.core.interfaces;

import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.CarRepository;
import easterRaces.repositories.interfaces.DriverRepository;
import easterRaces.repositories.interfaces.RaceRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl  implements Controller{
    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private RaceRepository raceRepository;

    public ControllerImpl(DriverRepository driverRepository, CarRepository carRepository,
                          RaceRepository raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        for (Driver driver1 : driverRepository.getAll()) {
            if (driver1.getName().equals(driver)) {
                throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
            }
        }

        Driver currDriver = new DriverImpl(driver);
        driverRepository.add(currDriver);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        for (Car car : carRepository.getAll()) {
            if (car.getModel().equals(model)) {
                throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
            }
        }

        Car car = null;
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else if (type.equals("Sports")) {
            car = new SportsCar(model, horsePower);
        }

        carRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = null;
        for (Driver currDriver : driverRepository.getAll()) {
            if (currDriver.getName().equals(driverName)) {
                driver = currDriver;
                break;
            }
        }

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = null;
        for (Car currCar : carRepository.getAll()) {
            if (currCar.getModel().equals(carModel)) {
                car = currCar;
                break;
            }
        }

        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = null;
        for (Race currRace : raceRepository.getAll()) {
            if (currRace.getName().equals(raceName)) {
                race = currRace;
                break;
            }
        }

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = null;
        for (Driver currDriver : driverRepository.getAll()) {
            if (currDriver.getName().equals(driverName)) {
                driver = currDriver;
                break;
            }
        }

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        for (Race currRace : raceRepository.getAll()) {
            if (currRace.getName().equals(name)) {
                throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
            }
        }

        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = null;

        for (Race currRace : raceRepository.getAll()) {
            if (currRace.getName().equals(raceName)) {
                race = currRace;
                break;
            }
        }

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Collection<Driver> drivers = race.getDrivers();

        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        final int lapsCount = race.getLaps();
        ArrayList<Driver> driverArrayList = drivers.stream()
                .sorted((d1, d2) ->
                        Double.compare(d2.getCar().calculateRacePoints(lapsCount),
                                d1.getCar().calculateRacePoints(lapsCount)))
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));


        StringBuilder output = new StringBuilder();
        String line=System.lineSeparator();

        output.append(String.format(DRIVER_FIRST_POSITION, driverArrayList.get(0).getName(), raceName))
                .append(line);
        output.append(String.format(DRIVER_SECOND_POSITION, driverArrayList.get(1).getName(), raceName))
                .append(line);
        output.append(String.format(DRIVER_THIRD_POSITION, driverArrayList.get(2).getName(), raceName))
                .append(line);

        return output.toString().trim();
    }
}
