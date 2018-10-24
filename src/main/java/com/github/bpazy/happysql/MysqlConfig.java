package com.github.bpazy.happysql;

import java.util.ResourceBundle;

public class MysqlConfig {
    private static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("mysql");
    }

    public static String getUser() {
        return getBundle().getString("user");
    }

    public static String getPassword() {
        return getBundle().getString("password");
    }

    public static String getUrl() {
        return getBundle().getString("url");
    }

    public static String getDriverClassName() {
        return getBundle().getString("driverClassName");
    }
}
