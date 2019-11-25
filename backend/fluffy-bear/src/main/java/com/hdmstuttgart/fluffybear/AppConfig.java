package com.hdmstuttgart.fluffybear;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private boolean isDemoMode;

    private static AppConfig instance;

    private AppConfig() {
        loadConfig();
    }

    public static synchronized AppConfig getInstance () {
        if (AppConfig.instance == null) {
            AppConfig.instance = new AppConfig();
        }
        return AppConfig.instance;
    }

    private void loadConfig() {
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:application.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.isDemoMode = Boolean.parseBoolean(properties.getProperty("demomode"));
    }

    public boolean isDemoMode() {
        return isDemoMode;
    }
}