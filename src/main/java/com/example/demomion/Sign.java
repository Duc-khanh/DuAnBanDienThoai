package com.example.demomion;

import javafx.fxml.FXML;

import javafx.scene.control.*;



public class Sign {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private Button signButton;
    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        signButton.setOnAction(actionEvent -> handleSign());
        backButton.setOnAction(actionEvent -> handleBack());
    }

    private void handleSign() {
        if (Username.getText().isEmpty() || Password.getText().isEmpty() || ConfirmPassword.getText().isEmpty()) {
            System.out.println("Không được để trống");
        } else {
            System.out.println("Đăng ký thành công");
            try {
                Main.changeScene("Login.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleBack() {
        try {
            Main.changeScene("Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}