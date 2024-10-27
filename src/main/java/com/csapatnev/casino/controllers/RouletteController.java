package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Random;

public class RouletteController {

    @FXML
    private Group polygonGroup;

    @FXML
    private Button btnSwitchToMain;

    @FXML
    private Text rollText;

    @FXML
    public void initialize() {
        // Set up the RotateTransition

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(200), polygonGroup);

        rotateTransition.setByAngle(3600); // Spin 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);

        rotateTransition.setAutoReverse(false); // No reverse
        rotateTransition.play(); // Start the animation
    }

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();
        Stage stage = (Stage) btnSwitchToMain.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
    }

    public void Roll()
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(5), polygonGroup);
        rotateTransition.setByAngle(360); // Spin 360 degrees
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false); // No reverse

        Random random = new Random();
        rotateTransition.setOnFinished(event -> {
            int randomNumber = getRandomNumber();
            rollText.setText(String.valueOf(randomNumber));
        });

        rotateTransition.play();
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(37);
    }
}
