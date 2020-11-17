package Logger.interfaces;

import Logger.enums.ReportLevel;

public interface Layout {
    String format(String date, ReportLevel reportLevel, String message);
}
