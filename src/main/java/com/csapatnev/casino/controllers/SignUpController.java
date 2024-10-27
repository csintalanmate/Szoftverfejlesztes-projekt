package com.csapatnev.casino.controllers;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.event.ActionEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Controller
public class SignUpController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    @FXML
    private PasswordField password;

    @FXML
    void reset(ActionEvent event) {
        resetFields();
    }

    @FXML
    void saveUser(ActionEvent event) {
        saveUserHelp();
    }

    @FXML
    public void saveUserHelp() {
        // Validate user input
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || dob.getValue() == null ||
                email.getText().isEmpty() || password.getText().isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        User user = new User();
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
        user.setDob(dob.getValue());
        user.setEmail(email.getText());
        user.setPassword(password.getText()); // Will be hashed in service
        user.setGender(male.isSelected() ? "Male" : "Female");
        user.setRole("USER"); // Assign a default role

        userService.save(user);
        showAlert("Success", "User registered successfully!");
        resetFields();
    }

    private void resetFields() {
        firstName.clear();
        lastName.clear();
        dob.setValue(null);
        email.clear();
        password.clear();
        male.setSelected(false);
        female.setSelected(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
