package easterRaces.repositories.interfaces;

import easterRaces.entities.cars.Car;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CarRepository implements Repository<Car>{

    private Map<String, Car> cars;

    public CarRepository() {
      this.cars= new HashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.cars.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return this.cars.values();
    }

    @Override
    public void add(Car model) {
      this.cars.put(model.getModel(),model);
    }

    @Override
    public boolean remove(Car model) {
        return this.cars.remove(model.getModel(),model);
    }
}
