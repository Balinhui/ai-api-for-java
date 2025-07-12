package org.balinhui.util;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DialogueFile {
    @Getter
    private static final File logDirectory = new File("history");
    @Getter
    private static final String fileName = "dialogue.md";

    protected DialogueFile() {}

    static {
        createDir();
    }

    public static @NotNull String[] getDialogueList() {
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
