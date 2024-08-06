package com.example.demomion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;


public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Main.primaryStage = primaryStage;
            Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml"), "FXML file not found"));
            primaryStage.setScene(new Scene(root1, 720, 480));
            root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource("logo.css"), "CSS file not found").toExternalForm());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeScene(String fxml) throws Exception {
        Parent pane = FXMLLoader.load(Main.class.getResource(fxml));
        primaryStage.setScene(new Scene(pane));
        pane.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("logo.css"), "CSS file not found").toExternalForm());


    }


    public static void main(String[] args) {
        launch(args);
    }
}
