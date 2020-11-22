package BarracksWarsCommandsStrikeBack.core.commands;

import BarracksWarsCommandsStrikeBack.interfaces.Executable;
import BarracksWarsCommandsStrikeBack.interfaces.Inject;
import BarracksWarsCommandsStrikeBack.interfaces.Repository;

public class Retire implements Executable {
    @Inject
    private String[] data;
    @Inject
    private Repository repository;

    public Retire() {

    }

    public Retire(String[] data, Repository repository) {
        this.data = data;
        this.repository = repository;
    }

    @Override
    public String execute() {
        String unitType = data[1];
        repository.removeUnit(unitType);
        return unitType + " retired!";
    }
}
