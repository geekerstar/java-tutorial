package com.geekerstar.design.principle.compositionaggregation;

/**
 * @author geekerstar
 * date: 2019/1/6 16:59
 * description:
 */
public class ProductDao {
    private DBConnection dbConnection;

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addProduct(){
        String conn = dbConnection.getConnection();
        System.out.println("使用"+conn+"增加产品");

    }
}
