package com.csapatnev.casino.testable;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.services.UserService;
import com.csapatnev.casino.utils.AdminInserter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class TestableLoginController {

    @Autowired
    private final UserService userService;

    @Autowired
    public TestableLoginController(UserService userService, AdminInserter adminInserter) {
        this.userService = userService;
    }

    @FXML
    private String email;

    @FXML
    private String password;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;


    @FXML
    public void login() throws IOException {
        String emailText = email;
        String passwordText = password;


        if (userService.authenticate(emailText, passwordText)) {
            if (emailText.equals("admin@admin") && passwordText.equals("admin")) {
                System.out.println("ADMIN");
            }
            else {
                System.out.println("USER");
            }
        }

        else {
            System.out.println("INVALID");
        }
    }


}
