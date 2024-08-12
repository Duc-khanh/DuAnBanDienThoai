package com.example.demomion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.io.FileWriter;
import java.io.IOException;

public class OrderListController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> productColumn;
    @FXML
    private TableColumn<Order, Integer> quantityColumn;
    @FXML
    private TableColumn<Order, Double> totalPriceColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private TableColumn<Order, Void> actionColumn;

    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadOrdersFromDatabase(); // Tải dữ liệu từ OrderDatabase

        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button payButton = new Button("Đã thanh toán");
            private final Button cancelButton = new Button("Hủy đơn");

            {
                payButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    if (order.getStatus().equals("Chờ thanh toán")) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn chuyển trạng thái đơn hàng thành Đã thanh toán?", ButtonType.OK, ButtonType.CANCEL);
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                order.setStatus("Đã thanh toán");
                                orderTable.refresh();
                                payButton.setVisible(false);
                                cancelButton.setVisible(false);
                                saveOrdersToFile(); // Lưu thông tin hóa đơn sau khi chỉnh sửa
                            }
                        });
                    }
                });

                cancelButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    if (order.getStatus().equals("Chờ thanh toán")) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn chuyển trạng thái đơn hàng thành Hủy đơn?", ButtonType.OK, ButtonType.CANCEL);
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                orders.remove(order); // Xóa đơn hàng khỏi danh sách
                                orderTable.refresh();
                                saveOrdersToFile(); // Lưu thông tin hóa đơn sau khi chỉnh sửa
                            }
                        });
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Order order = getTableView().getItems().get(getIndex());
                    if (order.getStatus().equals("Chờ thanh toán")) {
                        payButton.setVisible(true);
                        cancelButton.setVisible(true);
                    } else {
                        payButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }
                    HBox hBox = new HBox(payButton, cancelButton);
                    setGraphic(hBox);
                }
            }
        });

        orderTable.setItems(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
        saveOrdersToFile(); // Lưu thông tin hóa đơn sau khi thêm mới
    }

    public ObservableList<Order> getAllOrders() {
        return orders;
    }

    private void saveOrdersToFile() {
        try (FileWriter writer = new FileWriter("order.txt", false)) {
            for (Order order : orders) {
                writer.write(order.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            showError("Failed to save orders: " + e.getMessage());
        }
    }

    private void loadOrdersFromDatabase() {
        OrderDatabase orderDatabase = new OrderDatabase();
        orders.addAll(orderDatabase.getOrders());
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
