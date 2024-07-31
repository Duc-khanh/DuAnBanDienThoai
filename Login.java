package com.demo3.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private void initialize(){
        loginButton.setOnAction(actionEvent -> handleLogin());
        signButton.setOnAction(actionEvent -> handleSign());
    }
    private void handleLogin(){
        if(Username.getText().isEmpty() || Password.getText().isEmpty()){
            System.out.println("Điền đầy đủ thông tin để đăng nhập");
        }
        else if (Username.equals("Admin") && Password.equals("Password")){
            System.out.println("Đăng nhập thành công");
        }
        else {
            System.out.println("Đăng nhập thất bại");
        }
    }
    private void handleSign(){
        try{
            Main.changeScene("Sign.fxml");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
