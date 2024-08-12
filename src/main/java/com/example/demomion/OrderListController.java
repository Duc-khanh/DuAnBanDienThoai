package com.example.demomion;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.Parent;


import java.io.IOException;

public class OrderListController {
    @FXML
    private ListView<Order> orderListView;

    public void initialize() {
        OrderDatabase db = new OrderDatabase();
        orderListView.getItems().addAll(db.getOrders());
        orderListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (empty || order == null) { //
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderCell.fxml"));
                        Parent root = loader.load();
                        OrderCellController controller = loader.getController();
                        controller.setOrder(order);
                        setGraphic(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(OrderApp.class, args);
    }
}
