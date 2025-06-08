package org.balinhui.util;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class LogFile {
    @Getter
    private static final File logDirectory = new File("history");
    @Getter
    private static final String fileName = "ai.log.md";

    protected LogFile() {}

    static {
        if (!logDirectory.isDirectory()) {
            if (!logDirectory.mkdir()) throw new RuntimeException("无法创建文件夹");
        }
    }

    public static @NotNull String[] getLogList() {
        return logDirectory.list() == null ? new String[0] : logDirectory.list();
    }
}
