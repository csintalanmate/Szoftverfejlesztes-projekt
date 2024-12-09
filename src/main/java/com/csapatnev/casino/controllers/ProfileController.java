package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class ProfileController {

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
    private TextField userName;

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);
        Parent mainPage = loader.load();
        Stage stage = (Stage) mainBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPage));
    }

    @FXML
    void updateProfile(ActionEvent event) {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() ||
                email.getText().isEmpty() || !email.getText().contains("@") || password.getText().isEmpty()) {
            showAlert("Error", "Please fill in all fields properly.");
            return;
        }
    }

}
