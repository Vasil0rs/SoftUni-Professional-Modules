package BarracksWarsCommandsStrikeBack.core.commands;

import BarracksWarsCommandsStrikeBack.interfaces.Executable;
import BarracksWarsCommandsStrikeBack.interfaces.Repository;

public class Report  implements Executable {

    private Repository repository;

    public Report(Repository repository) {
        this.repository = repository;
    }

    public Report() {

    }

    @Override
    public String execute() {
        return repository.getStatistics();
    }
}
