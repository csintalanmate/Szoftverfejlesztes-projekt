package com.csapatnev.casino.testable;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Getter
@Setter
@Controller
public class TestableSignUpController  {

    @Autowired
    private UserService userService;

    @Autowired
    public TestableSignUpController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private String firstName;

    @FXML
    private String lastName;

    @FXML
    private LocalDate dob;

    @FXML
    private String email;

    @FXML
    private boolean male;

    @FXML
    private boolean female;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReset;

    @FXML
    private String password;



    @FXML
    public void saveUserHelp() {
        // Validate user input
        if (firstName.isEmpty() || lastName.isEmpty() || dob == null ||
                email.isEmpty() || !email.contains("@") || password.isEmpty()) {
            return;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDob(dob);
        user.setEmail(email);
        user.setPassword(password); // Will be hashed in service
        user.setGender(male ? "Male" : "Female");
        user.setRole("USER"); // Assign a default role

        userService.save(user);
    }




}
