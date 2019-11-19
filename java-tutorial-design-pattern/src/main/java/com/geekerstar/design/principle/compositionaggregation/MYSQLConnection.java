package com.geekerstar.design.principle.compositionaggregation;

/**
 * @author geekerstar
 * date: 2019/1/6 17:04
 * description:
 */
public class MYSQLConnection extends DBConnection{

    @Override
    public String getConnection() {
        return "MYSQL数据库连接";
    }
}
