package org.balinhui.util;

import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Logger extends LogFile {
    private String name = getFileName();
    private boolean action = false;
    @Getter
    private static final Logger logger = new Logger();

    private Logger() {
    }


    public void log(String log) {
        try {
            renameFile();
            FileWriter fileWriter = new FileWriter(getLogDirectory() + File.separator + name);
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renameFile() {
        if(action) return;
        int i = 1;
        while (equalName()) {
            name = "(" + i + ")" + getFileName();
            i++;
        }
        action = true;
    }

    private boolean equalName() {
        for (String s : Objects.requireNonNull(getLogDirectory().list()))
            if (s.equals(name)) return true;
        return false;
    }
}
