package org.balinhui.core;

import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final File logFile = new File("history");
    private static final String fileName = "ai.log.md";
    private String name = fileName;
    private boolean action = false;
    @Getter
    private static final Logger logger = new Logger();

    static {
        if (!logFile.isDirectory()) {
            if (!logFile.mkdir()) throw new RuntimeException("无法创建文件夹");
        }
    }

    private Logger() {
    }

    public static String[] getLogList() {
        return logFile.list();
    }

    public void log(String log) {
        try {
            renameFile();
            FileWriter fileWriter = new FileWriter(logFile + "\\" + name);
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renameFile() {
        if(action) return;
        int i = 1;
        while (true) {
            if (changeName()) break;
            name = "(" + i + ")" + fileName;
            i++;
        }
        action = true;
    }

    private boolean changeName() {
        for (String s : logFile.list())
            if (s.equals(name)) return false;
        return true;
    }
}
