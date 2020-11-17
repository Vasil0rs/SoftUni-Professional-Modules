package Logger;

import Logger.controllers.ConsoleAppender;
import Logger.controllers.MessageLogger;
import Logger.controllers.SimpleLayout;
import Logger.interfaces.Appender;
import Logger.interfaces.Layout;
import Logger.interfaces.Logger;

public class Main {
    public static void main(String[] args) {
        Layout simpleLayout = new SimpleLayout();
        Appender consoleAppender = new ConsoleAppender(simpleLayout);
        Logger logger = new MessageLogger(consoleAppender);

        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");
    }
}
