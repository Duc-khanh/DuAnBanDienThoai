package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainLayoutController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Chào mừng đến với shop bán điện thoại của N5!");
    }
}
