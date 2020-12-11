package easterRaces.repositories.interfaces;

import easterRaces.entities.drivers.Driver;

import java.util.*;

public class DriverRepository implements Repository<Driver> {
    private Map<String,Driver> drivers;

    public DriverRepository() {
        this.drivers=new HashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.drivers.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return this.drivers.values();
    }

    @Override
    public void add(Driver model) {
       this.drivers.put(model.getName(),model);
    }

    @Override
    public boolean remove(Driver model) {
        return this.drivers.remove(model.getName(),model);
    }
}
