package com.github.bpazy.happysql;

import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.github.bpazy.happysql.MysqlConfig.*;

public class SqlContext {
    @Getter private Connection conn;

    @SneakyThrows
    public SqlContext() {
        conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
    }
}
