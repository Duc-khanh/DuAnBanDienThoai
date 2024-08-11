package com.example.demomion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {
    @FXML
    private AnchorPane contentArea;

    @FXML
    private TextField text;

    @FXML
    private ImageView imageView;

    // Phương thức xử lý sự kiện
    @FXML
    private void showDashboard() {
        // Mã để hiển thị giao diện bảng điều khiển
        System.out.println("Đang hiển thị Bảng điều khiển");
    }

    @FXML
    private void showProducts() {
        try {
            // Tải nội dung của ProductList.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuProduct.fxml"));
            Parent productListView = loader.load();

            // Thay thế nội dung hiện tại của contentArea bằng ProductList.fxml
            contentArea.getChildren().clear();
            contentArea.getChildren().add(productListView);
        } catch (IOException e) {
            showErrorAlert("Error loading Product List", e.getMessage());
        }
    }

    @FXML
    private void showOrders() {
        System.out.println("Đang hiển thị Đơn hàng");
    }

    @FXML
    private void showEmployees() {
        // Mã để hiển thị giao diện nhân viên
        System.out.println("Đang hiển thị Nhân viên");
    }
    @FXML
    private void showInvoices() {
        // Mã để hiển thị giao diện hóa đơn
        try {
            // Tải nội dung của Order.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Order.fxml"));
            Parent orderView = loader.load();

            // Thay thế nội dung hiện tại của contentArea bằng Order.fxml
            contentArea.getChildren().clear();
            contentArea.getChildren().add(orderView);
        } catch (IOException e) {
            showErrorAlert("Error loading Order", e.getMessage());
        }
        System.out.println("Đang hiển thị Hóa đơn");
    }

    @FXML
    private void showInvoiceDetails() {
        // Mã để hiển thị chi tiết hóa đơn
        System.out.println("Đang hiển thị Chi tiết hóa đơn");
    }

    @FXML
    private void logout() {
        // Xác nhận đăng xuất
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có muốn đăng xuất?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText(null);

        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            // Mã để xử lý đăng xuất
            System.out.println("Đang đăng xuất...");
            try {
                Main.changeScene("Login.fxml");
            } catch (Exception e) {
                showErrorAlert("Error during logout", e.getMessage());
            }
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
