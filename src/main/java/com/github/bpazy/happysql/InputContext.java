package com.github.bpazy.happysql;

import com.github.bpazy.happysql.ognl.OgnlUtils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class InputContext {
    private String sql;
    private SqlContext sqlContext;
    private Class returnType;

    @SneakyThrows
    public InputContext(SqlContext sqlContext,Class returnType, String sql) {
        this.sql = sql;
        this.sqlContext = sqlContext;
        this.returnType = returnType;
    }

    private boolean isSelect() {
        return StringUtils.startsWithIgnoreCase(sql, "select");
    }

    private boolean isUpdate() {
        return StringUtils.startsWithIgnoreCase(sql, "update");
    }

    private boolean isDelete() {
        return StringUtils.startsWithIgnoreCase(sql, "delete");
    }

    private boolean isInsert() {
        return StringUtils.startsWithIgnoreCase(sql, "insert");
    }

    @SneakyThrows
    public ResultContext execute() {
        PreparedStatement pstmt = sqlContext.getConn().prepareStatement(sql);

        if (isSelect()) {
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<Object> retList = new ArrayList<>();
            while (rs.next()) {
                Object retValue = returnType.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String fieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                    Object queryValue = rs.getObject(i);
                    OgnlUtils.setValue(fieldName, retValue, queryValue);
                    retList.add(retValue);
                }
            }
            return new ResultContext(retList);
        }

        if (isUpdate() || isDelete() || isInsert()) {
            return new ResultContext(Lists.newArrayList(pstmt.executeUpdate()));
        }
        throw new RuntimeException("Wrong sql");
    }
}
