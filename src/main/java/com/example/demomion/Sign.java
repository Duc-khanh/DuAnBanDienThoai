package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Sign extends Login {
    @FXML
    private TextField Username;
    @FXML
    private TextField Email;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private Button signButton;
    @FXML
    private Button backButton;
    @FXML
    private int count = 5;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^\\d{10}$";

    @FXML
    private void initialize() {
        signButton.setOnAction(actionEvent -> handleSign());
        backButton.setOnAction(actionEvent -> handleLogin());


    }

    private void handleSign() {
        String username = Username.getText();
        String email = Email.getText();
        String phoneNumber = PhoneNumber.getText();
        String password = Password.getText();
        String confirmPassword = ConfirmPassword.getText();
        String id = String.valueOf(count);
        if(findUserBoolean(username)){
            showAlert("Username is already in use!");
        }
        else if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Không được để chống các mục");
        } else if (username.length() < 8) {
            showAlert("Tên không được dưới 8 kí tự");
        } else if (!isValidEmail(email)) {
            showAlert("Địa chỉ email không hợp lệ.");
        } else if (!isValidPhoneNumber(phoneNumber)) {
            showAlert("Số điện thoại không hợp lệ. Số điện thoại phải gồm 10 chữ số.");
        } else if (password.length() < 8) {
            showAlert("Password không được dưới 8 kí tự");
        } else if (!password.equals(confirmPassword)) {
            showAlert("Mật Khẩu không khớp");
        } else {
            showAlert("Đăng kí thành công");
            User newuser = new User(username, password,email, phoneNumber, id);
            users = Arrays.copyOf(users, users.length + 1);
            users[users.length - 1] = newuser;
            showUser();
            try{
                Main.changeScene("Login.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void handleLogin() {
        try {
            Main.changeScene("Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches(PHONE_REGEX, phoneNumber);
    }
}