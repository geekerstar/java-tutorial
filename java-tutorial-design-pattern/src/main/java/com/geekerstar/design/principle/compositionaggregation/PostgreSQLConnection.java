package com.geekerstar.design.principle.compositionaggregation;

/**
 * @author geekerstar
 * date: 2019/1/6 17:04
 * description:
 */
public class PostgreSQLConnection extends DBConnection {
    @Override
    public String getConnection() {
        return "PostgreSQL数据库连接";
    }
}
