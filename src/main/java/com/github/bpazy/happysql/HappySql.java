package com.github.bpazy.happysql;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class HappySql {
    private final static String driver = "com.mysql.jdbc.Driver";

    @SneakyThrows
    public static void main(String[] args) {
        Class.forName(driver);

        Connection conn = getConnection();
        conn.prepareStatement()
    }

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection("db4free.net", "ziyuan", "1");
    }
}
