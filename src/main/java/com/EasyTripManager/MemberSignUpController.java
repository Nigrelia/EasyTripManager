package com.EasyTripManager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.sql.*;

public class MemberSignUpController {
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField companyNameField;
    @FXML private RadioButton companyRadioButton;
    @FXML private RadioButton individualRadioButton;
    @FXML private Button signUpButton;

    @FXML
    public void initialize() {
        // Create toggle group for radio buttons
        ToggleGroup accountTypeGroup = new ToggleGroup();
        companyRadioButton.setToggleGroup(accountTypeGroup);
        individualRadioButton.setToggleGroup(accountTypeGroup);
        individualRadioButton.setSelected(true);

        // Enable/disable company name field based on radio button selection
        companyRadioButton.selectedProperty().addListener((obs, oldVal, newVal) -> 
            companyNameField.setDisable(!newVal));
    }

    @FXML
    private void onSignUpButtonClick(ActionEvent event) {
        if (!validateInputs()) {
            return;
        }

        String accountType = companyRadioButton.isSelected() ? "Company" : "Individual";
        Member member = new Member(
            fullNameField.getText(),
            emailField.getText(),
            passwordField.getText(),
            companyNameField.getText().trim().isEmpty() ? null : companyNameField.getText(),
            accountType
        );

        if (saveMemberToDatabase(member)) {
            showAlert(Alert.AlertType.INFORMATION, "Account Created Successfully", 
                     "Welcome to EasyTrip Manager!\n\n" +
                     "Your account has been created successfully.\n" +
                     "Your Account ID is: " + member.getAccountId() + "\n\n" +
                     "Please keep this ID for future reference.");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", 
                     "We couldn't create your account at this time.\n" +
                     "Please try again later or contact support.");
        }
    }

    private boolean validateInputs() {
        if (fullNameField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Full name is required");
            return false;
        }
        if (emailField.getText().trim().isEmpty() || !emailField.getText().contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Valid email is required");
            return false;
        }
        if (passwordField.getText().length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", 
                     "Password must be at least 6 characters long");
            return false;
        }
        if (companyRadioButton.isSelected() && companyNameField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Company name is required");
            return false;
        }
        return true;
    }

    private boolean saveMemberToDatabase(Member member) {
        String sql = "INSERT INTO members (account_id, full_name, email, password, company_name, account_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, member.getAccountId());
            pstmt.setString(2, member.getFullName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPassword());
            pstmt.setString(5, member.getCompanyName());
            pstmt.setString(6, member.getAccountType());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        fullNameField.clear();
        emailField.clear();
        passwordField.clear();
        companyNameField.clear();
        individualRadioButton.setSelected(true);
    }
}