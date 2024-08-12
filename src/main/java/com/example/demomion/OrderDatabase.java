package com.example.demomion;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {

        private List<Order> orders;

        public OrderDatabase() {
            orders = new ArrayList<>();
            // Thêm dữ liệu giả lập
            orders.add(new Order( "Pham Van A","Xiaomi POCO X6 5G 12GB-256GB", 2, "Chờ thanh toán",2000));
            orders.add(new Order("Pham Van B","Samsung Galaxy M55 5G 256GB", 1, "Đã thanh toán",2500));
            orders.add(new Order("Pham Van C","Samsung Galaxy S24 Plus 5G 256GB",3,"Chờ thanh toán",3000));
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
