package com.javaprocess.examples.integration.impl;

import com.javaprocess.examples.integration.pojos.Order;
import com.javaprocess.examples.integration.pojos.OrderConfirmation;
import com.javaprocess.examples.integration.interfaces.IOrderService;

import java.util.ArrayList;
import java.util.List;

public class App {

    public class AppWorker implements Runnable {
        public void run() {
            long threadId=Thread.currentThread().getId();
            System.out.println("Requesting order processing on thread: " + threadId);
            Order order=new Order();
            order.setId(100);
            OrderConfirmation ret= orderService.placeOrder(order);
            System.out.println("Order was requested by " + threadId+"  and by processed by thread: " + ret.getThreadHandler());
        }
    }

    private IOrderService orderService;

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public void run() {
        List<Thread> list=new ArrayList<Thread>();
        for (int i=0;i<5;i++) {
            Thread thread=new Thread(new AppWorker());
            thread.start();
            list.add(thread);

        }
        for (Thread  thread:list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
