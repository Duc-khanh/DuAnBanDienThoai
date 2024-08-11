package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;

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
    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean findUserBoolean(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    protected boolean login() {
        String username = this.Username.getText();
        String password = this.Password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (!findUserBoolean(username)) {
            alert.setContentText("Username không tồn tại.");
            alert.show();
            return false;
        } else {
            if (findUser(username).getUsername().equals(username) && findUser(username).getPassword().equals(password)) {
                if (findUser(username).getRole().equalsIgnoreCase("admin")) {
                    alert.setContentText("Success! Xin chào admin " + findUser(username).getUsername() + ".");
                    showUser();
                } else {
                    alert.setContentText("Success! Xin chào user " + findUser(username).getUsername() + ".");
                }
            } else {
                alert.setContentText("Incorrect username or password!");
                alert.show();
                return false;
            }
        }
        alert.show();
        return true;
    }

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
    }


    static User user1 = new Admin("PhamThom", "123456789", "vykid9@gmail.com", "0867536601", "admin");
    static User user2 = new User("NguyenKhanh", "987654321", "Khanh01@gmail.com", "0123456789", "user");
    static User user3 = new User("TuanMinh", "66668888", "Minh02@gmail.com", "0987654321", "user");
    static User user4 = new User("NgocThom", "88886666", "NgocThom03@gmail.com", "024683579", "user");
    static User user5 = new User("DucKhanh01", "22224444", "Khanh02@gmail.com", "013572468", "user");

    public static User[] users = {user1, user2, user3, user4, user5};
    public void add(User user) {
        users = Arrays.copyOf(users,users.length + 1);
        users[users.length - 1] = user;
    }


    private void handleLogin() {
        if(login()) {
            try {
                Main.changeScene("Home.fxml");
            } catch (Exception e) {
                e.printStackTrace();
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
    public void showUser(){
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}

