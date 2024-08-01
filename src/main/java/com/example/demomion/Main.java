package com.demo3.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Main.primaryStage = primaryStage;
            Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sign.fxml"), "FXML file not found"));
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

    }

    public static void main(String[] args) {
        launch(args);
    }
}
