package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button mainBtn;

    @FXML
    private TextField password;

    @FXML
    private Button updateBtn;



    @FXML
    public void switchToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            loader.setControllerFactory(AppContextProvider::getBean);
            Parent mainPage = loader.load();
            Stage stage = (Stage) mainBtn.getScene().getWindow();
            stage.setScene(new Scene(mainPage));
        } catch (IOException e) {
            showAlert("Error", "Failed to load the main page.");
            e.printStackTrace();
        }
    }

    @FXML
    public void updateProfile(ActionEvent event) {
        //if (firstName.getText().isEmpty() || lastName.getText().isEmpty() ||
        //        email.getText().isEmpty() || !email.getText().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$") || password.getText().isEmpty()) {
        //    showAlert("Error", "Please fill in all fields properly.");
        //}
        //else {
            User user = userService.findByEmail(email.getText());
            if (user == null) {
                showAlert("Error", "User not found with this email.");
                return;
            }

            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setPassword(password.getText());
            userService.update(user);

            showAlert("Success", "Profile updated successfully.");
        //}
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
