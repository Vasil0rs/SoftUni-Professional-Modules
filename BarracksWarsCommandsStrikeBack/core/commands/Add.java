package BarracksWarsCommandsStrikeBack.core.commands;

import BarracksWarsCommandsStrikeBack.interfaces.Executable;
import BarracksWarsCommandsStrikeBack.interfaces.Repository;
import BarracksWarsCommandsStrikeBack.interfaces.Unit;
import BarracksWarsCommandsStrikeBack.interfaces.UnitFactory;


public class Add implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    public Add() {

    }

    public Add(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public String execute() {
        String unitType = data[1];
        Unit unitToAdd = unitFactory.createUnit(unitType);
        repository.addUnit(unitToAdd);
        return unitType + " added!";
    }
}
