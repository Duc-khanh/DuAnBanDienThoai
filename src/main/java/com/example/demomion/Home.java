package com.example.demomion;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class Home {
    @FXML
    private MenuItem LogOut;
    @FXML
    private void initialize() {
        LogOut.setOnAction(actionEvent -> handHome());
    }
    public void handHome(){
        try {
            Main.changeScene("Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
