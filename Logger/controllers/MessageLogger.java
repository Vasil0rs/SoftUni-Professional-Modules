package Logger.controllers;

import Logger.enums.ReportLevel;
import Logger.interfaces.Appender;
import Logger.interfaces.Logger;

import java.util.Arrays;
import java.util.List;

public class MessageLogger implements Logger {
    private List<Appender> appender;

    public MessageLogger(Appender...appenders){
        this.appender= Arrays.asList(appenders);
    }


    @Override
    public void logInfo(String date, String message) {
        for (Appender appender1 : appender) {
            appender1.append(date, ReportLevel.INFO,message);
        }
    }

    @Override
    public void logWarning(String date, String message) {
        for (Appender appender1 : appender) {
            appender1.append(date, ReportLevel.WARNING,message);
        }
    }

    @Override
    public void logError(String date, String message) {
        for (Appender appender1 : appender) {
            appender1.append(date, ReportLevel.ERROR,message);
        }
    }

    @Override
    public void logCritical(String date, String message) {
        for (Appender appender1 : appender) {
            appender1.append(date, ReportLevel.CRITICAL,message);
        }
    }

    @Override
    public void logFatal(String date, String message) {
        for (Appender appender1 : appender) {
            appender1.append(date, ReportLevel.FATAL,message);
        }
    }
}
