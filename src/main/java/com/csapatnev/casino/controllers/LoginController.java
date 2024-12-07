package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.services.UserService;
import com.csapatnev.casino.utils.AdminInserter;
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
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService, AdminInserter adminInserter) {
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
    public void login(ActionEvent event) throws IOException {
        String emailText = email.getText();
        String passwordText = password.getText();


        if (userService.authenticate(emailText, passwordText)) {
            if (emailText.equals("admin@admin") && passwordText.equals("admin")) {
                showAlert("Admin Login", "Welcome, Admin!");
                switchToAdmin();
            }
            else {
                showAlert("Success", "Login successful!");
                switchToMain(); // Switch to the main application scene
            }
        }

        else {
            showAlert("Error", "Invalid email or password.");
        }
    }

    @FXML
    public void switchToAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);
        Parent adminRoot = loader.load();

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setScene(new Scene(adminRoot));
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
