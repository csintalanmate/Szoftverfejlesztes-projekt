package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.UserFX;
import com.csapatnev.casino.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.io.IOException;

@Component
public class UpdateController {

    private UserFX currentUser;

    @Autowired
    private final UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private PasswordField password;

    @FXML
    private TextField role;

    @FXML
    private Button btnSave;

    @FXML
    private void initialize() {
        // Handle gender selection defaults
        if (currentUser != null) {
            prepopulateFields();
        }
    }

    public void setUser(UserFX user) {
        this.currentUser = user;
        prepopulateFields();
    }

    private void prepopulateFields() {
        if (currentUser != null) {
            firstName.setText(currentUser.firstNameProperty().get());
            lastName.setText(currentUser.lastNameProperty().get());
            email.setText(currentUser.emailProperty().get());
            dob.setValue(currentUser.dobProperty().get());
            password.setText(currentUser.passwordProperty().get());
            role.setText(currentUser.roleProperty().get());

            if ("male".equalsIgnoreCase(currentUser.genderProperty().get())) {
                male.setSelected(true);
            } else if ("female".equalsIgnoreCase(currentUser.genderProperty().get())) {
                female.setSelected(true);
            }
        }
    }

    @FXML
    private void saveUser(javafx.event.ActionEvent actionEvent) {
        if (currentUser != null) {
            currentUser.firstNameProperty().set(firstName.getText());
            currentUser.lastNameProperty().set((lastName.getText()));
            currentUser.emailProperty().set((email.getText()));
            currentUser.dobProperty().set(dob.getValue());
            currentUser.passwordProperty().set((password.getText()));
            currentUser.genderProperty().set(male.isSelected() ? "male" : "female");
            currentUser.roleProperty().set(role.getText());

            userService.update(currentUser.toUser()); // Call service to persist the changes

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User updated successfully!");
            alert.showAndWait();

            // Return back to the previous admin view
            switchBackToAdmin();
        }
    }

    private void switchBackToAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin.fxml"));
            loader.setControllerFactory(AppContextProvider::getBean);
            Parent adminRoot = loader.load();
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.setScene(new Scene(adminRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateUser(javafx.event.ActionEvent actionEvent) {
        saveUser(actionEvent);
    }
}


