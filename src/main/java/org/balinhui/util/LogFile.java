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
        createDir();
    }

    public static @NotNull String[] getLogList() {
        return logDirectory.list() == null ? new String[0] : logDirectory.list();
    }

    public static void createDir() {
        if (!logDirectory.exists()) {
            if (!logDirectory.mkdir()) throw new RuntimeException("无法创建文件夹");
        }
        if (!logDirectory.isDirectory()) {
            throw new RuntimeException("history不是文件夹而是文件，请把名为history的文件移动至别处或删除");
        }
    }
}
