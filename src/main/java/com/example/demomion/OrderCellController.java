package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class OrderCellController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> productInfo;
    @FXML
    private TableColumn<Order, Integer> quantity;
    @FXML
    private TableColumn<Order, Double> totalPrice;
    @FXML
    private TableColumn<Order, Void> actionColumnCell;
    @FXML
    private Order order;
    private Button paidButton;
    private Button cancelButton;

    @FXML
    public void initialize() {
        productInfo.setCellValueFactory(new PropertyValueFactory<>("productInfo"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        actionColumnCell.setCellFactory(col -> new TableCell<Order, Void>() {
            {
                paidButton = new Button("Đã thanh toán");
                cancelButton = new Button("Hủy Đơn");

                paidButton.setOnAction(event -> showConfirmationDialog("Đã thanh toán"));
                cancelButton.setOnAction(event -> showConfirmationDialog("Hủy đơn"));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(paidButton, cancelButton);
                    setGraphic(buttons);
                }
            }
        });
    }

    public void setOrder(Order order) {
        this.order = order;
        productInfo.setText(order.getProductInfo());
        quantity.setText(String.valueOf(order.getQuantity()));
        totalPrice.setText(String.valueOf(order.getTotalPrice()));

        // Khởi tạo các nút trước khi sử dụng
        if (paidButton == null) {
            paidButton = new Button("Đã thanh toán");
        }
        if (cancelButton == null) {
            cancelButton = new Button("Hủy Đơn");
        }

        if ("Chờ thanh toán".equals(order.getStatus())) {
            paidButton.setDisable(false);
            cancelButton.setDisable(false);
        } else {
            paidButton.setDisable(true);
            cancelButton.setDisable(true);
        }
    }

    private void showConfirmationDialog(String newStatus) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn chuyển trạng thái đơn hàng thành " + newStatus + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                order.setStatus(newStatus);
                updateOrderStatusInDatabase(order);
            }
        });
    }

    private void updateOrderStatusInDatabase(Order order) {
        // Logic cập nhật trạng thái đơn hàng trong database
    }
}