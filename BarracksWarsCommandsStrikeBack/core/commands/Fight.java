package BarracksWarsCommandsStrikeBack.core.commands;

import BarracksWarsCommandsStrikeBack.interfaces.Executable;

public class Fight implements Executable {
    public Fight() {
    }

    @Override
    public String execute() {
        return "fight";
    }
}
