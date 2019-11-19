package com.geekerstar.design.pattern.structural.proxy.staticproxy;

import com.geekerstar.design.pattern.structural.proxy.Order;


public class Test {
    public static void main(String[] args) {
        Order order = new Order();
        order.setUserId(2);

        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy();
        orderServiceStaticProxy.saveOrder(order);
    }
}
