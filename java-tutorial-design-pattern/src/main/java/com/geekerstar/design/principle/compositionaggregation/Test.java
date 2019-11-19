package com.geekerstar.design.principle.compositionaggregation;

/**
 * @author geekerstar
 * date: 2019/1/6 17:02
 * description:
 */
public class Test {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.setDbConnection(new MYSQLConnection());
        productDao.addProduct();
    }
}
