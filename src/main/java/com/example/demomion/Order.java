package com.example.demomion;

public class Order {
    private String customer;
    private String product;
    private int quantity;
    private double totalPrice;
    private String status; // "Chờ thanh toán", "Đã thanh toán", "Hủy đơn"

    // Constructor, getters, and setters
    public Order(String customer, String product, int quantity, String status , double totalPrice) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
