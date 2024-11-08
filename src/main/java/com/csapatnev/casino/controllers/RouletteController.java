package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    private Polygon number0Panel;
    @FXML
    private Polygon number1Panel;
    @FXML
    private Polygon number2Panel;
    @FXML
    private Polygon number3Panel;
    @FXML
    private Polygon number4Panel;
    @FXML
    private Polygon number5Panel;
    @FXML
    private Polygon number6Panel;
    @FXML
    private Polygon number7Panel;
    @FXML
    private Polygon number8Panel;
    @FXML
    private Polygon number9Panel;
    @FXML
    private Polygon number10Panel;
    @FXML
    private Polygon number11Panel;
    @FXML
    private Polygon number12Panel;
    @FXML
    private Polygon number13Panel;
    @FXML
    private Polygon number14Panel;
    @FXML
    private Polygon number15Panel;
    @FXML
    private Polygon number16Panel;
    @FXML
    private Polygon number17Panel;
    @FXML
    private Polygon number18Panel;
    @FXML
    private Polygon number19Panel;
    @FXML
    private Polygon number20Panel;
    @FXML
    private Polygon number21Panel;
    @FXML
    private Polygon number22Panel;
    @FXML
    private Polygon number23Panel;
    @FXML
    private Polygon number24Panel;
    @FXML
    private Polygon number25Panel;
    @FXML
    private Polygon number26Panel;
    @FXML
    private Polygon number27Panel;
    @FXML
    private Polygon number28Panel;
    @FXML
    private Polygon number29Panel;
    @FXML
    private Polygon number30Panel;
    @FXML
    private Polygon number31Panel;
    @FXML
    private Polygon number32Panel;
    @FXML
    private Polygon number33Panel;
    @FXML
    private Polygon number34Panel;
    @FXML
    private Polygon number35Panel;
    @FXML
    private Polygon number36Panel;


    @FXML
    private Button btnSwitchToMain;

    @FXML
    private Text rollText;


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

            rollText.setText(String.valueOf(order[(finalRandomNumber*2)%37]));

        });

        RotateTransition rotateTransitionBall = new RotateTransition(Duration.seconds(5), ball);
        rotateTransitionBall.setByAngle(turnWheel); // Spin 360 degrees
        rotateTransitionBall.setCycleCount(1);
        rotateTransitionBall.setAutoReverse(true);

        rotateTransitionBall.setOnFinished(event -> {
            // Get the current panel to update based on the number that was landed on
            switch (order[((finalRandomNumber * 2) % 37)]) {
                case 0:
                    winPanelColor(number0Panel);
                    break;
                case 1:
                    winPanelColor(number1Panel);
                    break;
                case 2:
                    winPanelColor(number2Panel);
                    break;
                case 3:
                    winPanelColor(number3Panel);
                    break;
                case 4:
                    winPanelColor(number4Panel);
                    break;
                case 5:
                    winPanelColor(number5Panel);
                    break;
                case 6:
                    winPanelColor(number6Panel);
                    break;
                case 7:
                    winPanelColor(number7Panel);
                    break;
                case 8:
                    winPanelColor(number8Panel);
                    break;
                case 9:
                    winPanelColor(number9Panel);
                    break;
                case 10:
                    winPanelColor(number10Panel);
                    break;
                case 11:
                    winPanelColor(number11Panel);
                    break;
                case 12:
                    winPanelColor(number12Panel);
                    break;
                case 13:
                    winPanelColor(number13Panel);
                    break;
                case 14:
                    winPanelColor(number14Panel);
                    break;
                case 15:
                    winPanelColor(number15Panel);
                    break;
                case 16:
                    winPanelColor(number16Panel);
                    break;
                case 17:
                    winPanelColor(number17Panel);
                    break;
                case 18:
                    winPanelColor(number18Panel);
                    break;
                case 19:
                    winPanelColor(number19Panel);
                    break;
                case 20:
                    winPanelColor(number20Panel);
                    break;
                case 21:
                    winPanelColor(number21Panel);
                    break;
                case 22:
                    winPanelColor(number22Panel);
                    break;
                case 23:
                    winPanelColor(number23Panel);
                    break;
                case 24:
                    winPanelColor(number24Panel);
                    break;
                case 25:
                    winPanelColor(number25Panel);
                    break;
                case 26:
                    winPanelColor(number26Panel);
                    break;
                case 27:
                    winPanelColor(number27Panel);
                    break;
                case 28:
                    winPanelColor(number28Panel);
                    break;
                case 29:
                    winPanelColor(number29Panel);
                    break;
                case 30:
                    winPanelColor(number30Panel);
                    break;
                case 31:
                    winPanelColor(number31Panel);
                    break;
                case 32:
                    winPanelColor(number32Panel);
                    break;
                case 33:
                    winPanelColor(number33Panel);
                    break;
                case 34:
                    winPanelColor(number34Panel);
                    break;
                case 35:
                    winPanelColor(number35Panel);
                    break;
                case 36:
                    winPanelColor(number36Panel);
                    break;
                default:
                    break;
            }
        });







        rotateTransitionBall.play();
        rotateTransitionWheel.play();

    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(37);
    }

    private void pause() throws InterruptedException {
        Thread.sleep(2000);
    }



    public void winPanelColor(Polygon numberPanel) {
        Paint originalColor = numberPanel.getFill();
        numberPanel.setFill(Color.YELLOW);
    }



}
