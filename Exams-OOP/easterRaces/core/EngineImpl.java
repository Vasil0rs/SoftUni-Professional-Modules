package easterRaces.core;

import easterRaces.common.Command;
import easterRaces.core.interfaces.Controller;
import easterRaces.core.interfaces.Engine;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.io.interfaces.InputReader;
import easterRaces.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;

import static easterRaces.common.ExceptionMessages.DRIVER_EXISTS;
import static easterRaces.common.OutputMessages.DRIVER_CREATED;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;

    public EngineImpl(InputReader reader, OutputWriter writer, Controller controller) {
        this.reader = reader;
        this.writer = writer;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals(Command.End.name())) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case AddDriverToRace:
                result = this.addDriver(data);
                break;
            case End:
                result = Command.End.name();
                break;
            case StartRace:
                result = this.startRace(data);
                break;
            case CreateDriver:
                result = this.createDriver(data);
                break;
            case AddCarToDriver:
                result = this.addCar(data);
                break;
            case CreateCar:
                result = this.createCar(data);
                break;
            case CreateRace:
                result = this.createRace(data);
                break;
        }


        return result;
    }

    private String createRace(String[] data) {
        String name = data[0];
        int laps = Integer.parseInt(data[1]);
        return controller.createRace(name, laps);
    }

    private String addDriver(String[] data) {
        String raceName = data[0];
        String driverName = data[1];
        return controller.addDriverToRace(raceName, driverName);
    }

    private String startRace(String[] data) {
        String raceName = data[0];
        return controller.startRace(raceName);

    }

    private String addCar(String[] data) {
        String driverName = data[0];
        String carName = data[1];
        return controller.addCarToDriver(driverName, carName);
    }

    private String createCar(String[] data) {
        String carType = data[0];
        String model = data[1];
        int horsePower = Integer.parseInt(data[2]);
        return controller.createCar(carType, model, horsePower);
    }

    private String createDriver(String[] data) {
        String name = data[0];
        return controller.createDriver(name);
    }
}