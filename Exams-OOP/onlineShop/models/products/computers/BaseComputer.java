package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;


    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();

    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(System.lineSeparator());
        output.append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, components.size()))
                .append(System.lineSeparator());

        for (Component component : components) {
            output.append("  ").append(component.toString()).append(System.lineSeparator());
        }

        output.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING, peripherals.size(), peripherals.stream()
                        .mapToDouble(Peripheral::getOverallPerformance).average().orElse(0)))
                .append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {
            output.append("  ").append(peripheral).append(System.lineSeparator());
        }

        return output.toString().trim();
    }

    @Override
    public Component removeComponent(String componentType) {
        if (components.stream().noneMatch(c -> c.getClass().getSimpleName().equals(componentType))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT
                    , componentType, this.getClass().getSimpleName(), this.getId()));
        }

        int index = 0;
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (component.getClass().getSimpleName().equals(componentType)) {
                index = i;
                break;
            }
        }
        return this.components.remove(index);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (peripherals.stream().noneMatch(c -> c.getClass().getSimpleName().equals(peripheralType))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL
                    , peripheralType, this.getClass().getSimpleName(), this.getId()));
        }

        int index = 0;
        for (int i = 0; i < peripherals.size(); i++) {
            Peripheral peripheral = peripherals.get(i);
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                index = i;
                break;
            }
        }
        return this.peripherals.remove(index);
    }

    @Override
    public void addComponent(Component component) {
        if (this.components.stream().anyMatch((c -> c.getClass() == component.getClass()))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT
                    , component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (this.peripherals.stream().anyMatch(c -> c.getClass() == peripheral.getClass())) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL
                    , peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public double getOverallPerformance() {
        double componentPerformance =
                components.stream()
                        .mapToDouble(Component::getOverallPerformance).average().orElse(0);
        return super.getOverallPerformance() + componentPerformance;
    }

    @Override
    public double getPrice() {
        double sum = components.stream().mapToDouble(Component::getPrice).sum();
        double sum1 = peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
        return super.getPrice() + sum + sum1;
    }


}
