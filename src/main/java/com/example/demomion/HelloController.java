package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label message;


    @FXML
    protected void login() {
        String usernameValue = username.getText();
        String passwordValue = password.getText();

        if (usernameValue.equals("khanhdepchai") && passwordValue.equals("123123")) {
            message.setText("Đăng nhập thành công");
        } else {
            message.setText("Đăng nhập thất bại");
        }

    }
}
