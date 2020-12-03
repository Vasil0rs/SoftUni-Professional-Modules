package robotService.models.procedures.interfaces;

import robotService.common.ExceptionMessages;
import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseProcedure implements Procedure {
    protected Collection<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName());
        String separator= System.lineSeparator();
        for (Robot robot : robots) {
          stringBuilder.append(robot.toString()).append(separator);
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME);
        }
        int newTime = robot.getProcedureTime() - procedureTime;
        robot.setProcedureTime(newTime);
        this.robots.add(robot);
    }

}
