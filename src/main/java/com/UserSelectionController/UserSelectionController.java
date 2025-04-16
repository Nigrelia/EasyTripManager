package com.EasyTripManager;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;

public class UserSelectionController {

    @FXML
    public void onAdminButtonClick(ActionEvent event) {
        showPopup("Admin Button Clicked", "You clicked the Admin button.");
    }

    @FXML
    public void onMemberButtonClick(ActionEvent event) {
        try {
            // Load the MemberSignUp.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/easytripmanager/MemberSignUp.fxml"));
            Parent root = fxmlLoader.load();

            // Create and show a new stage (window)
            Stage newStage = new Stage();
            newStage.setTitle("Member Sign Up");
            newStage.setScene(new Scene(root));
            
            // Get the current window and close it
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            
            // Show the new window
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showPopup("Error", "Could not load MemberSignUp window: " + e.getMessage());
        }
    }

    private void showPopup(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}