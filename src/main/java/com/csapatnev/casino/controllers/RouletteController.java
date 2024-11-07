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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Random;

public class RouletteController {

    @FXML
    private Group polygonGroupBigRoulette;

    @FXML
    private Group ball;

    @FXML
    private Polygon numberZeroPanel;

    @FXML
    private Button btnSwitchToMain;

    @FXML
    private Text rollText;

    public boolean pause = false;

    @FXML
    public void initialize() {
        // Set up the RotateTransition
        /*
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(20), polygonGroup);

        rotateTransition.setByAngle(360); // Spin 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);

        rotateTransition.setAutoReverse(false); // No reverse
        rotateTransition.play(); // Start the animation
        */
    }

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();
        Stage stage = (Stage) btnSwitchToMain.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
    }

    int startNumber = 0;
    int[] order = {0,32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26};


    public void Roll()
    {

        int randomNumber = getRandomNumber();



        double turnWheel = randomNumber * 9.7297297297 + 360;


        RotateTransition rotateTransitionWheel = new RotateTransition(Duration.seconds(5), polygonGroupBigRoulette);
        rotateTransitionWheel.setByAngle(-turnWheel); // Spin 360 degrees
        rotateTransitionWheel.setCycleCount(1);
        rotateTransitionWheel.setAutoReverse(false); // No reverse

        int finalRandomNumber = randomNumber + startNumber;
        startNumber = finalRandomNumber;
        rotateTransitionWheel.setOnFinished(event -> {

            rollText.setText(String.valueOf(order[(finalRandomNumber%37)*2]));

        });

        RotateTransition rotateTransitionBall = new RotateTransition(Duration.seconds(5), ball);
        rotateTransitionBall.setByAngle(turnWheel); // Spin 360 degrees
        rotateTransitionBall.setCycleCount(1);
        rotateTransitionBall.setAutoReverse(true);


        rotateTransitionBall.setOnFinished(event -> {
            numberZeroPanel.setFill(Color.YELLOW);
        });



        rotateTransitionBall.play();
        rotateTransitionWheel.play();

    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(37);
    }
}
