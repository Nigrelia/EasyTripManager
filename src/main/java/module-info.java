module com.example.easytripmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.EasyTripManager to javafx.fxml, javafx.graphics;
    exports com.EasyTripManager;
}
