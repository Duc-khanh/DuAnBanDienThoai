package com.example.demomion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//
//        // Thiết lập kích thước cửa sổ theo kích thước màn hình
//        primaryStage.setWidth(screenBounds.getWidth());
//        primaryStage.setHeight(screenBounds.getHeight());
//
//        // Đảm bảo cửa sổ luôn ở giữa màn hình
//        primaryStage.setX(screenBounds.getMinX());
//        primaryStage.setY(screenBounds.getMinY());
        try {
            Main.primaryStage = primaryStage;
            Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeTest.fxml"), "FXML file not found"));
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

    public static void showUsersFile() {
        String filePath = "Users.txt"; // Đường dẫn tới tệp Users.txt

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

//            Files.write(Paths.get(filePath), new byte[0]);
//            System.out.println("Đã xóa toàn bộ dữ liệu trong tệp Users.txt");
//            Xóa dữ liệu trong file Users.text
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

