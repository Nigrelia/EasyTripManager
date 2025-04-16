package com.EasyTripManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Run database diagnostics


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/easytripmanager/UserSelection.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EasyTrip Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}