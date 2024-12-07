package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RouletteController {


    @FXML
    private Group polygonGroupBigRoulette;

    @FXML
    private Group ball;
    //region Panels
    @FXML
    private Rectangle evenRect;
    @FXML
    private Rectangle oddRect;
    @FXML
    private Rectangle redRect;
    @FXML
    private Rectangle blackRect;

    @FXML
    private Polygon zeroPanel;
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
    private Rectangle number1Rect;
    @FXML
    private Rectangle number2Rect;
    @FXML
    private Rectangle number3Rect;
    @FXML
    private Rectangle number4Rect;
    @FXML
    private Rectangle number5Rect;
    @FXML
    private Rectangle number6Rect;
    @FXML
    private Rectangle number7Rect;
    @FXML
    private Rectangle number8Rect;
    @FXML
    private Rectangle number9Rect;
    @FXML
    private Rectangle number10Rect;
    @FXML
    private Rectangle number11Rect;
    @FXML
    private Rectangle number12Rect;
    @FXML
    private Rectangle number13Rect;
    @FXML
    private Rectangle number14Rect;
    @FXML
    private Rectangle number15Rect;
    @FXML
    private Rectangle number16Rect;
    @FXML
    private Rectangle number17Rect;
    @FXML
    private Rectangle number18Rect;
    @FXML
    private Rectangle number19Rect;
    @FXML
    private Rectangle number20Rect;
    @FXML
    private Rectangle number21Rect;
    @FXML
    private Rectangle number22Rect;
    @FXML
    private Rectangle number23Rect;
    @FXML
    private Rectangle number24Rect;
    @FXML
    private Rectangle number25Rect;
    @FXML
    private Rectangle number26Rect;
    @FXML
    private Rectangle number27Rect;
    @FXML
    private Rectangle number28Rect;
    @FXML
    private Rectangle number29Rect;
    @FXML
    private Rectangle number30Rect;
    @FXML
    private Rectangle number31Rect;
    @FXML
    private Rectangle number32Rect;
    @FXML
    private Rectangle number33Rect;
    @FXML
    private Rectangle number34Rect;
    @FXML
    private Rectangle number35Rect;
    @FXML
    private Rectangle number36Rect;
    //endregion

    @FXML
    private Circle TokenCircle;
    @FXML
    private Button btnSwitchToMain;
    @FXML
    private Text rollText;
    @FXML
    private Text blacktext;
    @FXML
    private Text tokenText;
    @FXML
    private Text token100Text;
    @FXML
    private Text balanceText;

    private double offsetX, offsetY;
    int startNumber = 0;
    int[] order = {0,32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26};
    List<String> blackNumbers = Arrays.asList("2","4","6","8","10","11","13","15","17","20","22","24","26","28","29","31","33","35");
    List<String> redNumbers = Arrays.asList("1","3","5","7","9","12","14","16","18","19","21","23","25","27","30","32","34","36");
    int balance = 5000;
    int betAmount = 100;
    boolean betPlaced = false;
    List<Polygon> blackpoly = new ArrayList<>();
    List<Polygon> redpoly = new ArrayList<>();
    List<Rectangle> blackrect = new ArrayList<>();
    List<Rectangle> redrect = new ArrayList<>();
    List<Rectangle> transparentrect = new ArrayList<>();
    private boolean isDragging = false;
    int finalRandomNumber;

    boolean isBlack;


    @FXML
    public void initialize() {

        blacktext.setText("");
        betOnBox = "";


        TokenCircle.setOnMousePressed(event -> handleMousePressed(event, TokenCircle));
        TokenCircle.setOnMouseReleased(event -> handleMouseReleased(TokenCircle));
        TokenCircle.setOnMouseDragged(event -> handleMouseDragged(event, TokenCircle, blacktext));
        updateBalance();

        //region redpoly,rect blackpoly,rect
        transparentrect.add(oddRect);
        transparentrect.add(evenRect);
        transparentrect.add(redRect);
        transparentrect.add(blackRect);


        redpoly.add(number1Panel);
        redpoly.add(number3Panel);
        redpoly.add(number5Panel);
        redpoly.add(number7Panel);
        redpoly.add(number9Panel);
        redpoly.add(number12Panel);
        redpoly.add(number14Panel);
        redpoly.add(number16Panel);
        redpoly.add(number18Panel);
        redpoly.add(number19Panel);
        redpoly.add(number21Panel);
        redpoly.add(number23Panel);
        redpoly.add(number25Panel);
        redpoly.add(number27Panel);
        redpoly.add(number30Panel);
        redpoly.add(number32Panel);
        redpoly.add(number34Panel);
        redpoly.add(number36Panel);

        redrect.add(number1Rect);
        redrect.add(number3Rect);
        redrect.add(number5Rect);
        redrect.add(number7Rect);
        redrect.add(number9Rect);
        redrect.add(number12Rect);
        redrect.add(number14Rect);
        redrect.add(number16Rect);
        redrect.add(number18Rect);
        redrect.add(number19Rect);
        redrect.add(number21Rect);
        redrect.add(number23Rect);
        redrect.add(number25Rect);
        redrect.add(number27Rect);
        redrect.add(number30Rect);
        redrect.add(number32Rect);
        redrect.add(number34Rect);
        redrect.add(number36Rect);


        blackpoly.add(number2Panel);
        blackpoly.add(number4Panel);
        blackpoly.add(number6Panel);
        blackpoly.add(number8Panel);
        blackpoly.add(number10Panel);
        blackpoly.add(number11Panel);
        blackpoly.add(number13Panel);
        blackpoly.add(number15Panel);
        blackpoly.add(number17Panel);
        blackpoly.add(number20Panel);
        blackpoly.add(number22Panel);
        blackpoly.add(number24Panel);
        blackpoly.add(number26Panel);
        blackpoly.add(number28Panel);
        blackpoly.add(number29Panel);
        blackpoly.add(number31Panel);
        blackpoly.add(number33Panel);
        blackpoly.add(number35Panel);
        blackrect.add(number2Rect);
        blackrect.add(number4Rect);
        blackrect.add(number6Rect);
        blackrect.add(number8Rect);
        blackrect.add(number10Rect);
        blackrect.add(number11Rect);
        blackrect.add(number13Rect);
        blackrect.add(number15Rect);
        blackrect.add(number17Rect);
        blackrect.add(number20Rect);
        blackrect.add(number22Rect);
        blackrect.add(number24Rect);
        blackrect.add(number26Rect);
        blackrect.add(number28Rect);
        blackrect.add(number29Rect);
        blackrect.add(number31Rect);
        blackrect.add(number33Rect);
        blackrect.add(number35Rect);
        //endregion


    }

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();
        Stage stage = (Stage) btnSwitchToMain.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
        stage.centerOnScreen();
    }






    public void Roll()
    {

        setStartingColors();







        updateBalance();
        betPlaced = false;





        int randomNumber = getRandomNumber();
        //randomNumber = 6; //teszt


        double turnWheel = randomNumber * 9.7297297297 + 360;


        RotateTransition rotateTransitionWheel = new RotateTransition(Duration.seconds(5), polygonGroupBigRoulette);
        rotateTransitionWheel.setByAngle(-turnWheel); // Spin 360 degrees
        rotateTransitionWheel.setCycleCount(1);
        rotateTransitionWheel.setAutoReverse(false); // No reverse

        finalRandomNumber = randomNumber + startNumber;
        startNumber = finalRandomNumber;
        rotateTransitionWheel.setOnFinished(event -> rollText.setText(String.valueOf(order[(finalRandomNumber*2)%37])));

        RotateTransition rotateTransitionBall = new RotateTransition(Duration.seconds(5), ball);
        rotateTransitionBall.setByAngle(turnWheel); // Spin 360 degrees
        rotateTransitionBall.setCycleCount(1);
        rotateTransitionBall.setAutoReverse(true);

        rotateTransitionBall.setOnFinished(event -> {
            switch (order[((finalRandomNumber * 2) % 37)]) {
                case 0:
                    number0Panel.setFill(Color.YELLOW);
                    zeroPanel.setFill(Color.YELLOW);
                    break;
                case 1:
                    winPanelColor(number1Panel, number1Rect);
                    break;
                case 2:
                    winPanelColor(number2Panel, number2Rect);
                    break;
                case 3:
                    winPanelColor(number3Panel, number3Rect);
                    break;
                case 4:
                    winPanelColor(number4Panel, number4Rect);
                    break;
                case 5:
                    winPanelColor(number5Panel, number5Rect);
                    break;
                case 6:
                    winPanelColor(number6Panel, number6Rect);
                    break;
                case 7:
                    winPanelColor(number7Panel, number7Rect);
                    break;
                case 8:
                    winPanelColor(number8Panel, number8Rect);
                    break;
                case 9:
                    winPanelColor(number9Panel, number9Rect);
                    break;
                case 10:
                    winPanelColor(number10Panel, number10Rect);
                    break;
                case 11:
                    winPanelColor(number11Panel, number11Rect);
                    break;
                case 12:
                    winPanelColor(number12Panel, number12Rect);
                    break;
                case 13:
                    winPanelColor(number13Panel, number13Rect);
                    break;
                case 14:
                    winPanelColor(number14Panel, number14Rect);
                    break;
                case 15:
                    winPanelColor(number15Panel, number15Rect);
                    break;
                case 16:
                    winPanelColor(number16Panel, number16Rect);
                    break;
                case 17:
                    winPanelColor(number17Panel, number17Rect);
                    break;
                case 18:
                    winPanelColor(number18Panel, number18Rect);
                    break;
                case 19:
                    winPanelColor(number19Panel, number19Rect);
                    break;
                case 20:
                    winPanelColor(number20Panel, number20Rect);
                    break;
                case 21:
                    winPanelColor(number21Panel, number21Rect);
                    break;
                case 22:
                    winPanelColor(number22Panel, number22Rect);
                    break;
                case 23:
                    winPanelColor(number23Panel, number23Rect);
                    break;
                case 24:
                    winPanelColor(number24Panel, number24Rect);
                    break;
                case 25:
                    winPanelColor(number25Panel, number25Rect);
                    break;
                case 26:
                    winPanelColor(number26Panel, number26Rect);
                    break;
                case 27:
                    winPanelColor(number27Panel, number27Rect);
                    break;
                case 28:
                    winPanelColor(number28Panel, number28Rect);
                    break;
                case 29:
                    winPanelColor(number29Panel, number29Rect);
                    break;
                case 30:
                    winPanelColor(number30Panel, number30Rect);
                    break;
                case 31:
                    winPanelColor(number31Panel, number31Rect);
                    break;
                case 32:
                    winPanelColor(number32Panel, number32Rect);
                    break;
                case 33:
                    winPanelColor(number33Panel, number33Rect);
                    break;
                case 34:
                    winPanelColor(number34Panel, number34Rect);
                    break;
                case 35:
                    winPanelColor(number35Panel, number35Rect);
                    break;
                case 36:
                    winPanelColor(number36Panel, number36Rect);
                    break;
                default:
                    break;
            }
            if(blackNumbers.contains(String.valueOf(order[(finalRandomNumber*2)%37])))
            {
                isBlack = true;
            }
            if(redNumbers.contains(String.valueOf(order[(finalRandomNumber*2)%37])))
            {
                isBlack = false;
            }

            checkForBet(order[(finalRandomNumber*2)%37]);
            updateBalance();
            TokenCircle.setCenterX(0);
            TokenCircle.setCenterY(0);
            token100Text.setX(0);
            token100Text.setY(0);
        });
        rotateTransitionBall.play();
        rotateTransitionWheel.play();
    }

    private void updateBalance() {

        balanceText.setText(String.valueOf(balance));
        tokenText.setText(String.valueOf(betAmount));

    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(37);
    }

    private void checkForBet(int randomNumber)
    {
        if(randomNumber % 2 == 0)
        {
            evenRect.setFill(Color.YELLOW);
        }
        else if(randomNumber % 2 == 1)
        {
            oddRect.setFill(Color.YELLOW);
        }
        betPlaced = true;


        if(betOnBox.equals(String.valueOf(randomNumber)))
        {
            blacktext.setText("Nyertél");
            balance += betAmount*38;
            betPlaced = false;
        }
        else if(redNumbers.contains(String.valueOf(randomNumber)) && (!isBlack || betOnBox.equals("red")))
        {
            redRect.setFill(Color.YELLOW);
            if(betOnBox.equals("red"))
            {
                blacktext.setText("Nyertél");
                balance += betAmount*2;
                betPlaced = false;
            }

        }
        else if(blackNumbers.contains(String.valueOf(randomNumber)) && (isBlack || betOnBox.equals("black")))
        {
            blackRect.setFill(Color.YELLOW);
            if(betOnBox.equals("black"))
            {
                blacktext.setText("Nyertél");
                balance += betAmount*2;
                betPlaced = false;
            }

        }
        else if(betOnBox.equals("odd") && randomNumber % 2 == 1)
        {
            oddRect.setFill(Color.YELLOW);
            blacktext.setText("Nyertél");
            balance += betAmount*2;
            betPlaced = false;
        }
        else if(betOnBox.equals("even") && randomNumber % 2 == 0)
        {
            evenRect.setFill(Color.YELLOW);
            blacktext.setText("Nyertél");
            balance += betAmount*2;
            betPlaced = false;
        }
        if(betPlaced && !betOnBox.isEmpty())
        {
            blacktext.setText("Vesztettél");
            betPlaced = false;
        }
        else
        {
            betPlaced = false;
        }

        updateBalance();
    }


    private void handleMousePressed(javafx.scene.input.MouseEvent event, Circle circle) {
        offsetX = event.getSceneX() - circle.getCenterX();
        offsetY = event.getSceneY() - circle.getCenterY();
        isDragging = true;
    }
    private void handleMouseReleased(Circle circle) {
        isDragging = false;
        if(betOnBox.isEmpty())
        {
            circle.setCenterX(0);
            circle.setCenterY(0);
            token100Text.setX(circle.getCenterX());
            token100Text.setY(circle.getCenterY());
            if(betPlaced)
            {
                balance += betAmount;
                betPlaced = false;
            }
            updateBalance();
        }

    }



    String betOnBox;

    private void handleMouseDragged(javafx.scene.input.MouseEvent event, Circle circle, Text blacktext) {
        if (isDragging)
        {



            double newX = event.getSceneX() - offsetX;
            double newY = event.getSceneY() - offsetY;


            circle.setCenterX(newX);
            circle.setCenterY(newY);

            double circleCenterX = circle.getCenterX();
            double circleCenterY = circle.getCenterY();
            double circleRadius = circle.getRadius();


            if (checkDistance(circleCenterX, circleCenterY, 332, -158) <= circleRadius + circleRadius) {
                circle.setCenterX(332);
                circle.setCenterY(-158);
                betOnBox = "0";
                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 376, 45) <= circleRadius + circleRadius) {
                circle.setCenterX(376);
                circle.setCenterY(45);
                betOnBox = "black";
                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 276, 45) <= circleRadius + circleRadius) {
                circle.setCenterX(276);
                circle.setCenterY(45);
                betOnBox = "red";
                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 176, 45) <= circleRadius + circleRadius) {
                circle.setCenterX(176);
                circle.setCenterY(45);
                betOnBox = "even";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 476, 45) <= circleRadius + circleRadius) {
                circle.setCenterX(476);
                circle.setCenterY(45);
                betOnBox = "odd";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 76, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(76);
                circle.setCenterY(-7);
                betOnBox = "1";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 76, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(76);
                circle.setCenterY(-61);
                betOnBox = "2";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 76, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(76);
                circle.setCenterY(-115);
                betOnBox = "3";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 121, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(121);
                circle.setCenterY(-7);
                betOnBox = "4";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 121, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(121);
                circle.setCenterY(-61);
                betOnBox = "5";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 121, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(121);
                circle.setCenterY(-115);
                betOnBox = "6";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 168, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(168);
                circle.setCenterY(-7);
                betOnBox = "7";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 168, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(168);
                circle.setCenterY(-61);
                betOnBox = "8";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 168, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(168);
                circle.setCenterY(-115);
                betOnBox = "9";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 215, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(215);
                circle.setCenterY(-7);
                betOnBox = "10";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 215, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(215);
                circle.setCenterY(-61);
                betOnBox = "11";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 215, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(215);
                circle.setCenterY(-115);
                betOnBox = "12";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 262, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(262);
                circle.setCenterY(-7);
                betOnBox = "13";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 262, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(262);
                circle.setCenterY(-61);
                betOnBox = "14";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 262, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(262);
                circle.setCenterY(-115);
                betOnBox = "15";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 309, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(309);
                circle.setCenterY(-7);
                betOnBox = "16";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 309, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(309);
                circle.setCenterY(-61);
                betOnBox = "17";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 309, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(309);
                circle.setCenterY(-115);
                betOnBox = "18";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 355, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(355);
                circle.setCenterY(-7);
                betOnBox = "19";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 355, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(355);
                circle.setCenterY(-61);
                betOnBox = "20";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 355, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(355);
                circle.setCenterY(-115);
                betOnBox = "21";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 401, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(401);
                circle.setCenterY(-7);
                betOnBox = "22";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 401, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(401);
                circle.setCenterY(-61);
                betOnBox = "23";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 401, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(401);
                circle.setCenterY(-115);
                betOnBox = "24";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 447, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(447);
                circle.setCenterY(-7);
                betOnBox = "25";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 447, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(447);
                circle.setCenterY(-61);
                betOnBox = "26";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 447, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(447);
                circle.setCenterY(-115);
                betOnBox = "27";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 494, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(494);
                circle.setCenterY(-7);
                betOnBox = "28";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 494, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(494);
                circle.setCenterY(-61);
                betOnBox = "29";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 494, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(494);
                circle.setCenterY(-115);
                betOnBox = "30";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 541, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(541);
                circle.setCenterY(-7);
                betOnBox = "31";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 541, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(541);
                circle.setCenterY(-61);
                betOnBox = "32";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 541, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(541);
                circle.setCenterY(-115);
                betOnBox = "33";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 588, -7) <= circleRadius + circleRadius) {
                circle.setCenterX(588);
                circle.setCenterY(-7);
                betOnBox = "34";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 588, -61) <= circleRadius + circleRadius) {
                circle.setCenterX(588);
                circle.setCenterY(-61);
                betOnBox = "35";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else if (checkDistance(circleCenterX, circleCenterY, 588, -115) <= circleRadius + circleRadius) {
                circle.setCenterX(588);
                circle.setCenterY(-115);
                betOnBox = "36";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            } else {
                betOnBox = "";

                setToken100Text(circle.getCenterX(), circle.getCenterY());
            }
            //blacktext.setText(betOnBox);//test
            //blacktext.setText(String.valueOf(circle.getCenterX()) + String.valueOf(circle.getCenterY())); teszt
            if (!(checkDistance(circleCenterX, circleCenterY, 0, 0) <= circleRadius + circleRadius) && !betPlaced) {

                balance = balance - betAmount;
                betPlaced = true;
                //tokenText.setText("100");

            }

            updateBalance();
        }


    }

    private double checkDistance(double circleCenterX, double circleCenterY, int checkX, int checkY)
    {
        return Point2D.distance(circleCenterX, circleCenterY, checkX, checkY);
    }


    private void winPanelColor(Polygon numberPanel,Rectangle numberRect) {
        numberPanel.setFill(Color.YELLOW);
        numberRect.setFill(Color.YELLOW);
    }

    private void setToken100Text(double circleCenterX, double circleCenterY)
    {
        token100Text.setX(circleCenterX);
        token100Text.setY(circleCenterY);
    }

    private void setStartingColors()
    {
        for(int i = 0; i < 18;i++)
        {
            blackpoly.get(i).setFill(Color.BLACK);
        }
        for(int i = 0; i < 18;i++)
        {
            redpoly.get(i).setFill(Color.RED);
        }
        for(int i = 0; i < 18;i++)
        {
            blackrect.get(i).setFill(Color.BLACK);
        }
        for(int i = 0; i < 18;i++)
        {
            redrect.get(i).setFill(Color.RED);
        }
        for(int i = 0; i < 4;i++)
        {
        transparentrect.get(i).setFill(Color.TRANSPARENT);
        }
        number0Panel.setFill(Color.GREEN);
        zeroPanel.setFill(Color.GREEN);

    }



}
