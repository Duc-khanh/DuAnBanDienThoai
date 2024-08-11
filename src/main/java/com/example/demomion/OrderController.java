package com.example.demomion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> orderDateColumn;
    @FXML
    private TableColumn<Order, Double> totalAmountColumn;
    @FXML
    private TextField searchField;

    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        // Thiết lập các cột của bảng
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        orderDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty());
        totalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmountProperty().asObject());

        // Thêm dữ liệu mẫu vào danh sách đơn hàng
        orderList.add(new Order("001", "Nguyen Van A", "2024-08-01", 100.0));
        orderList.add(new Order("002", "Tran Thi B", "2024-08-02", 200.0));
        orderList.add(new Order("003", "Le Van C", "2024-08-03", 300.0));

        // Sử dụng FilteredList để lọc dữ liệu
        FilteredList<Order> filteredData = new FilteredList<>(orderList, p -> true);

        // Liên kết FilteredList với TableView
        orderTable.setItems(filteredData);

        // Thêm bộ lọc tìm kiếm
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(order -> {
                // Nếu ô tìm kiếm trống, hiển thị tất cả đơn hàng
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // So sánh từ khóa tìm kiếm với các thuộc tính của đơn hàng
                String lowerCaseFilter = newValue.toLowerCase();

                if (order.getOrderId().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Lọc theo mã đơn hàng
                } else if (order.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Lọc theo tên khách hàng
                } else if (order.getOrderDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Lọc theo ngày đặt hàng
                } else if (String.valueOf(order.getTotalAmount()).contains(lowerCaseFilter)) {
                    return true; // Lọc theo tổng tiền
                }
                return false; // Không khớp với từ khóa tìm kiếm
            });
        });
    }

    @FXML
    private void handleSearch() {
        // Phương thức này có thể để trống vì bộ lọc đã được thêm vào trong initialize
    }

}
