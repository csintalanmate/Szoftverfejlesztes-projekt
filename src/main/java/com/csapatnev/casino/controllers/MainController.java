package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController {


    @FXML
    private Button gameSelector;

    @FXML
    private ImageView profilpic;

    @Autowired
    private UserService userService;

    @Autowired
    public MainController (UserService userService)
    {
        this.userService = userService;
    }

    @FXML
    void switchToProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profile.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();

        Stage stage = (Stage) profilpic.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
        stage.centerOnScreen();
    }

    @FXML
    public void switchToRoulette() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Roulette.fxml"));
        //loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();

        Stage stage = (Stage) gameSelector.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
        stage.centerOnScreen();
    }

    @FXML
    public void switchToSlot() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SlotMachine.fxml"));
        Parent slotMachineRoot = loader.load();

        Stage stage = (Stage) gameSelector.getScene().getWindow();
        stage.setScene(new Scene(slotMachineRoot));
    }

    @FXML
    public void switchToBlackjack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Blackjack.fxml"));
        //loader.setControllerFactory(AppContextProvider::getBean);  // Replace with your Spring context provider
        Parent signUpRoot = loader.load();

        Stage stage = (Stage) gameSelector.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
    }

    @FXML
    public void initialize() {

    }


}
