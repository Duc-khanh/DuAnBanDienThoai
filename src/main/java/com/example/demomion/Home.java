package com.example.demomion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Home {
    @FXML
    private MenuItem LogOut;
    @FXML
    private Button showApple;
    @FXML
    private VBox productContainer;
    @FXML
    private void initialize() {
        LogOut.setOnAction(actionEvent -> handLogout());
        showApple.setOnAction(actionEvent -> handShowApple());
    }
    public void handLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có muốn đăng xuất?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText(null);

        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            try {
                Main.changeScene("Login.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handShowApple() {
        try {
            VBox appleProducts = FXMLLoader.load(getClass().getResource("Apple.fxml"));
            productContainer.getChildren().setAll(appleProducts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDashboard(ActionEvent actionEvent) {
    }

    public void showProducts(ActionEvent actionEvent) {
    }

    public void showOrders(ActionEvent actionEvent) {
    }

    public void showEmployees(ActionEvent actionEvent) {
    }

    public void showInvoices(ActionEvent actionEvent) {
    }

    public void showInvoiceDetails(ActionEvent actionEvent) {
    }

    public void LogOut(ActionEvent actionEvent) {
    }
}