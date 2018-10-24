package com.github.bpazy.happysql;

import lombok.SneakyThrows;

import java.util.List;

import static com.github.bpazy.happysql.MysqlConfig.getDriverClassName;

public class HappySql {
    private Class returnType;
    private int maxSize;

    @SneakyThrows
    public HappySql() {
        Class.forName(getDriverClassName());
    }

    public HappySql returnType(Class returnType) {
        this.returnType = returnType;
        return this;
    }

    public HappySql limit(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public <T> T execute(String sql) {
        InputContext inputContext = new InputContext(new SqlContext(), returnType, sql);
        ResultContext resultContext = inputContext.execute();
        List retList = resultContext.getRetList();
        return maxSize == 1 ? (T) retList.get(0) : (T) retList;
    }
}
