package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class Sign extends Login {
    @FXML
    private TextField Username;
    @FXML
    private TextField Phone;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private Button signButton;
    @FXML
    private Button backButton;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^\\d{10}$";

    @FXML
    private void initialize() {
        signButton.setOnAction(actionEvent -> handleSign());
        backButton.setOnAction(actionEvent -> handleBack());
    }

    private void handleSign() {
        String username = Username.getText().trim();
        String phone = Phone.getText().trim();
        String email = Email.getText().trim();
        String password = Password.getText().trim();
        String confirmPassword = ConfirmPassword.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
            showAlert("Không được để trống");
        } else if (username.length() < 8) {
            showAlert("Tên không được dưới 8 kí tự");
        } else if (!isValidPhone(phone)) {
            showAlert("Số điện thoại không hợp lệ. Số điện thoại phải gồm 10 chữ số.");
        } else if (!isValidEmail(email)) {
            showAlert("Địa chỉ email không hợp lệ.");
        } else if (password.length() < 8) {
            showAlert("Password không được dưới 8 kí tự");
        } else if (!password.equals(confirmPassword)) {
            showAlert("Mật Khẩu không khớp");
        } else {
            if (registerUser(username, password, phone, email)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Đăng ký thành công");
                alert.setContentText("Bây giờ bạn có thể đăng nhập.");
                alert.showAndWait();

                try {
                    Main.changeScene("Login.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Username.clear();
                Password.clear();
                Email.clear();
                Phone.clear();
                ConfirmPassword.clear();
            } else {
                showAlert("Tên người dùng đã tồn tại.");
            }
        }
    }

    private boolean registerUser(String username, String password, String phone, String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true))) {
            writer.write(username + "," + password + "," + phone + "," + email);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidPhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    private void handleBack() {
        try {
            Main.changeScene("Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
