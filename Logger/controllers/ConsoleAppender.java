package Logger.controllers;

import Logger.enums.ReportLevel;
import Logger.interfaces.Appender;
import Logger.interfaces.Layout;

public class ConsoleAppender implements Appender {
    private Layout layout;

    public ConsoleAppender(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void append(String date, ReportLevel reportLevel,String message) {
        System.out.println(this.layout.format(date,reportLevel,message));
    }
}
