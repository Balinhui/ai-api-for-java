package org.balinhui.Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private final File logFile = new File("src\\main\\java\\org\\balinhui\\ai.log.md");
    private static final Logger logger = new Logger();

    private Logger() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public void log(String log) {
        try {
            FileWriter fileWriter = new FileWriter(logFile);
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
