package org.balinhui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Reader {
    private static Properties config;

    private Reader() {}

    public static void setConfigFile(String path) throws IOException {
        File file = new File(path);
        setConfigFile(file);
    }

    public static void setConfigFile(File file) throws IOException {
        if (!file.getName().endsWith(".properties"))
            throw new RuntimeException("需要配置文件");
        config = new Properties();
        config.load(new FileInputStream(file));
    }

    public static String getConfig(String key) {
        if (config == null)
            throw new RuntimeException("未设置配置文件");
        return config.getProperty(key);
    }

}
