module com.example.demomion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.demomion to javafx.fxml;
    exports com.example.demomion;
}