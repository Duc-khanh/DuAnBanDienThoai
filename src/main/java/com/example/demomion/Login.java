//package com.example.demomion;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Login {
//    @FXML
//    private TextField Username;
//    @FXML
//    private PasswordField Password;
//    @FXML
//    private Button loginButton;
//    @FXML
//    private Button signButton;
//    @FXML
//    private CheckBox showPassword;
//    @FXML
//    private TextField textField;
//    private static List<String[]> user = new ArrayList<>();
//
//    @FXML
//    private void initialize() {
//        listUser();
//        loginButton.setOnAction(actionEvent -> handleLogin());
//        signButton.setOnAction(actionEvent -> handleSign());
//
//        textField.setManaged(false);
//        textField.setVisible(false);
//
//        // Đồng bộ hóa nội dung giữa PasswordField và TextField
//        textField.textProperty().bindBidirectional(Password.textProperty());
//
//        showPassword.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
//            if (isSelected) {
//                textField.setVisible(true);
//                textField.setManaged(true);
//                Password.setVisible(false);
//                Password.setManaged(false);
//            } else {
//                textField.setVisible(false);
//                textField.setManaged(false);
//                Password.setVisible(true);
//                Password.setManaged(true);
//            }
//        });
//        Password.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!showPassword.isSelected()) {
//                textField.setText(newValue);
//            }
//        });
//
//        // Đồng bộ hóa PasswordField với TextField khi văn bản thay đổi
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (showPassword.isSelected()) {
//                Password.setText(newValue);
//            }
//        });
//    }
//
//    private void handleLogin() {
//        String username = this.Username.getText().trim();
//        String password = this.Password.getText().trim();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            showAlert("Tên đăng nhập và mật khẩu ko đc để trống");
//        } else if (username.length() < 8) {
//            showAlert("Tên đăng nhập phải trên 8 kí tự");
//        } else if (password.length() < 8) {
//            showAlert("Mật khẩu phải trên 8 kí tự");
//        } else {
//            boolean isValid = false;
//            for (String[] newUser : user) {
//                if (newUser[0].equals(username) && newUser[1].equals(password)) {
//                    isValid = true;
//                    break;
//                }
//            }
//            if (isValid) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setContentText("Đăng nhập thành công");
//                Username.clear();
//                Password.clear();
//                alert.showAndWait();
//                showUserList();
//                try {
//                    Main.changeScene("HomeTest.fxml");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
////                showAlert("Đăng nhập thất bại !");
//                showAlert("sai mật khẩu");
//                Password.clear();
//            }
//        }
//    }
//
//    private void handleSign() {
//        try {
//            Main.changeScene("Sign.fxml");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listUser() {
//        user.add(new String[]{"doquochuy", "123456789", "doquochuy@gmail.com","0743278462", "admin"});
//        user.add(new String[]{"levandan1", "987654321", "lavandan1@gmail.com","0108738465", "user"});
//        user.add(new String[]{"huyentrang", "111111111", "huyentrang@gmail.com","0829746352", "user"});
//        user.add(new String[]{"vungocanh", "333333333", "vungocanh@gmail.com","0849284736", "user"});
//        user.add(new String[]{"tiencong1", "555555555", "tiencong1@gmail.com","9473625174", "user"});
//    }
//    private void showAlert(String mesage) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText(mesage);
//        alert.show();
//    }
//
//    protected List<String[]> getUsers() {
//        return user;
//    }
//
//    protected void showUserList() {
//        for (String[] user : user) {
//            if (user[3].equals("admin")) {
//                System.out.println("Username: \"" + user[0] + ",\t" + " Password: " + user[1] + "\t," + "Email: " + user[2] + ",\t\t" + user[3] + ",\t\t" + " Role: " + user[4]);
//            } else {
//                System.out.println("Username: \"" + user[0] + ",\t" + " Password: " + user[1] + "\t," + "Email: " + user[2] + ",\t\t" +"SĐT : "+ user[3] + ",\t\t" + " Role: " + user[4]);
//            }
//        }
//    }
//}
package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Main.showUsersFile();
    }

    private void handleLogin() {
        String username = this.Username.getText().trim();
        String password = this.Password.getText().trim();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Tên đăng nhập và mật khẩu không được để trống");
        } else if (username.length() < 8) {
            showAlert("Tên đăng nhập phải trên 8 kí tự");
        } else if (password.length() < 8) {
            showAlert("Mật khẩu phải trên 8 kí tự");
        } else {
            boolean isValid = validateUser(username, password);
            if (isValid) {
                alert.setHeaderText("Đăng nhập thành công");
                alert.setContentText("Xin chào " +username);
                Username.clear();
                Password.clear();
                alert.showAndWait();
                try {
                    Main.changeScene("HomeTest.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.setHeaderText("Mời đăng nhập lại");
                alert.setContentText("Sai tên đăng nhập hoặc mật khẩu");
                Password.clear();
            }
        }
    }

    private boolean validateUser(String username, String password) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("Users.txt"));
            for (String line : lines) {
                String[] credentials = line.split(",");
                if (credentials.length == 4 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void handleSign() {
        try {
            Main.changeScene("Sign.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

}
