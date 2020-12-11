package easterRaces.repositories.interfaces;

import easterRaces.entities.racers.Race;
import java.util.*;

public class RaceRepository implements Repository<Race> {

    private Map<String, Race> races;

    public RaceRepository() {
        this.races = new HashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return this.races.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return this.races.values();
    }

    @Override
    public void add(Race model) {
        this.races.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        return this.races.remove(model.getName(), model);
    }
}
