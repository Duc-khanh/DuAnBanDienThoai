package com.example.demomion;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private List<Order> orders;

    public OrderDatabase() {
        orders = new ArrayList<>();
        // Thêm dữ liệu giả lập
        orders.add(new Order( "Xiaomi POCO X6 5G 12GB-256GB", 2, 200000, "Chờ thanh toán"));
        orders.add(new Order("Samsung Galaxy M55 5G 256GB", 1, 100000, "Đã thanh toán"));
        orders.add(new Order("Samsung Galaxy S24 Plus 5G 256GB",3,100000,"Chờ thanh toán"));
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
    public void removeOrder(Order order) {
        orders.remove(order);
    }
    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
