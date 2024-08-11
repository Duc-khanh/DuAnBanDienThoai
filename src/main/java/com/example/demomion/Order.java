package com.example.demomion;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;

public class Order {
    private final StringProperty orderId;
    private final StringProperty customerName;
    private final StringProperty orderDate;
    private final DoubleProperty totalAmount;

    public Order(String orderId, String customerName, String orderDate, double totalAmount) {
        this.orderId = new SimpleStringProperty(orderId);
        this.customerName = new SimpleStringProperty(customerName);
        this.orderDate = new SimpleStringProperty(orderDate);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
    }

    public String getOrderId() {
        return orderId.get();
    }

    public void setOrderId(String orderId) {
        this.orderId.set(orderId);
    }

    public StringProperty orderIdProperty() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public String getOrderDate() {
        return orderDate.get();
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.set(orderDate);
    }

    public StringProperty orderDateProperty() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }
}
