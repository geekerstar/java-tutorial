package com.geekerstar.design.pattern.structural.proxy;


public class OrderServiceImpl implements IOrderService {
    private IOrderDao iOrderDao;


    public int saveOrder(Order order) {
        //Spring会自己注入，我们课程中就直接new了
        iOrderDao = new OrderDaoImpl();
        System.out.println("Service层调用Dao层添加Order");
        return iOrderDao.insert(order);
    }

}
