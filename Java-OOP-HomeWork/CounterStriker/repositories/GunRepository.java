package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GunRepository implements Repository<Gun> {
    private List<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }


    @Override
    public Collection<Gun> getModels() {
        return this.models;
    }

    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }

        models.add(model);
    }

    @Override
    public boolean remove(Gun model) {
        if (models.contains(model)) {
            return models.remove(model);
        }
        return false;
    }

    @Override
    public Gun findByName(String name) {
        return models.stream().filter(n -> n.getName().equals(name)).findFirst().orElse(null);
    }
}
