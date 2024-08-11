package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

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

    private static List<String[]> user = new ArrayList<>();

    public Login() {
    }

    @FXML
    private void initialize() {
        listUser();
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
        Password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!showPassword.isSelected()) {
                textField.setText(newValue);
            }
        });

        // Đồng bộ hóa PasswordField với TextField khi văn bản thay đổi
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (showPassword.isSelected()) {
                Password.setText(newValue);
            }
        });
    }

    public void listUser() {
        user.add(new String[]{"PhamThom", "123456789", "vykid9@gmail.com", "0867536601", "admin"});
        user.add(new String[]{"NguyenKhanh", "987654321", "Khanh01@gmail.com", "0123456789", "user"});
        user.add(new String[]{"TuanMinh", "66668888", "Minh02@gmail.com", "0987654321", "user"});
        user.add(new String[]{"NgocThom", "88886666", "NgocThom03@gmail.com", "024683579", "user"});
        user.add(new String[]{"DucKhanh01", "22224444", "Khanh02@gmail.com", "013572468", "user"});
    }

    private void handleLogin() {
        String username = Username.getText();
        String password = Password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Tên đăng nhập và mật khẩu ko đc để trống");
        } else if (username.length() < 8) {
            showAlert("Tên đăng nhập phải trên 8 kí tự");
        } else if (password.length() < 8) {
            showAlert("Mật khẩu phải trên 8 kí tự");
        } else {
            boolean isValid = false;
            for (String[] newUser : user) {
                if (newUser[0].equals(username) && newUser[1].equals(password)) {
                    isValid = true;
                    break;
                }
            }
            if (isValid) {
                showAlert("Đăng nhập thành công");
                Username.clear();
                Password.clear();
                showUserList();
                try {
                    Main.changeScene("Home.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showAlert("sai mật khẩu");
                Password.clear();
            }
        }
    }

    private void handleSign() {
        try {
            Main.changeScene("Sign.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String mesage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mesage);
        alert.show();
    }

    protected List<String[]> getUsers() {
        return user;
    }

    protected void showUserList() {
        for (String[] user : user) {
            if (user[3].equals("admin")) {
                System.out.println("Username: \"" + user[0] + ",\t" + " Password: " + user[1] + "\t," + "Email: " + user[2] + ",\t\t" + user[3] + ",\t\t" + " Role: " + user[4]);
            } else {
                System.out.println("Username: \"" + user[0] + ",\t" + " Password: " + user[1] + "\t," + "Email: " + user[2] + ",\t\t" + "SĐT : " + user[3] + ",\t\t" + " Role: " + user[4]);
            }
        }
    }
}

