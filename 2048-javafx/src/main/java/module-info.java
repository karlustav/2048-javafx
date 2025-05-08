module com.example.javafx2028 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javafx2028 to javafx.fxml;
    exports com.example.javafx2028;
}