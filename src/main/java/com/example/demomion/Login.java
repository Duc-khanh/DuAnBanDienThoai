package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Login {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Button loginButton;
    @FXML
    private Button signButton;
    @FXML
    private CheckBox showPassword;
    @FXML
    private TextField textField;

    @FXML
    private void initialize() {
        loginButton.setOnAction(actionEvent -> handleLogin());
        signButton.setOnAction(actionEvent -> handleSign());

        textField.setManaged(false);
        textField.setVisible(false);

        // Đồng bộ hóa nội dung giữa PasswordField và TextField
        textField.textProperty().bindBidirectional(Password.textProperty());

        showPassword.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                textField.setVisible(true);
                textField.setManaged(true);
                Password.setVisible(false);
                Password.setManaged(false);
            } else {
                textField.setVisible(false);
                textField.setManaged(false);
                Password.setVisible(true);
                Password.setManaged(true);
            }
        });
    }

//        // Lấy giá trị của email và password
//        var email = document.getElementById("email").value;
//        var password = document.getElementById("password").value;
//
//        // Biểu thức chính quy để kiểm tra email
//        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
//        if (!emailPattern.test(email)) {
//            alert("Email không hợp lệ");
//            return false;
//        }
//
//        // Biểu thức chính quy để kiểm tra password
//        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
//        if (!passwordPattern.test(password)) {
//            alert("Password phải có ít nhất 8 ký tự, bao gồm chữ cái và số");
//            return false;
//        }
//
//        // Nếu tất cả đều hợp lệ
//        return true;


    private void handleLogin() {
        if (Username.getText().isEmpty() || Password.getText().isEmpty()) {
            System.out.println("Điền đầy đủ thông tin để đăng nhập");
        } else if (Username.getText().equals("Admin") && Password.getText().equals("1234")) {
            System.out.println("Đăng nhập thành công");
            try {
                Main.changeScene("HomeTest.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Đăng nhập thất bại");
        }
    }

    private void handleSign() {
        try {
            Main.changeScene("Sign.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}