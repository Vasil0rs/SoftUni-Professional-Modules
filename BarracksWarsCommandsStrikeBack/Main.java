package BarracksWarsCommandsStrikeBack;

import BarracksWarsCommandsStrikeBack.core.CommandModel;
import BarracksWarsCommandsStrikeBack.interfaces.CommandInterpreter;
import BarracksWarsCommandsStrikeBack.interfaces.Repository;
import BarracksWarsCommandsStrikeBack.interfaces.Runnable;
import BarracksWarsCommandsStrikeBack.interfaces.UnitFactory;
import BarracksWarsCommandsStrikeBack.core.Engine;
import BarracksWarsCommandsStrikeBack.core.factories.UnitFactoryImpl;
import BarracksWarsCommandsStrikeBack.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        CommandInterpreter commandModel = new CommandModel(repository,unitFactory);

        Runnable engine = new Engine(commandModel);
        engine.run();
    }
}
