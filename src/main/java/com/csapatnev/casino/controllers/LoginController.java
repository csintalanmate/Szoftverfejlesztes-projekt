package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    public void login(ActionEvent event) {
        String emailText = email.getText();
        String passwordText = password.getText();

        if (userService.authenticate(emailText, passwordText)) {
            showAlert("Success", "Login successful!");
             // Switch to the main application scene
        } else {
            showAlert("Error", "Invalid email or password.");
        }
    }

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
    }

    @FXML
    public void switchToSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignUp.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();

        Stage stage = (Stage) btnSignUp.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void signUp(ActionEvent event) throws IOException{
        switchToSignUp();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
