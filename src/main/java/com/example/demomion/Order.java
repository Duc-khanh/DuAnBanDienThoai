package com.example.demomion;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {
    private final SimpleStringProperty productInfo;
    private final SimpleIntegerProperty quantity;
    private final SimpleDoubleProperty totalPrice;
    private String status;

    public Order(String productInfo, int quantity, double totalPrice, String chờThanhToán) {
        this.productInfo = new SimpleStringProperty(productInfo);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
        this.status = "Chờ thanh toán";
    }

    public String getProductInfo() {
        return productInfo.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
