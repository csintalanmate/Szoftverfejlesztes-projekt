package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class SlotMachineController {
    @FXML
    private Label slot1_1, slot1_2, slot1_3;
    @FXML
    private Label slot2_1, slot2_2, slot2_3;
    @FXML
    private Label slot3_1, slot3_2, slot3_3;

    @FXML
    private Button btnSwitchToMain;
    private final Random random = new Random();
    private final String[] symbols = {"🍒", "🍋", "🍊", "🍉", "⭐", "🔔"};
    private Timeline timeline;

    @FXML
    public void initialize() {
        updateFirstRow();
    }

    @FXML
    private void spin() {
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> spinSlots()));
        timeline.setCycleCount(20);
        timeline.setOnFinished(e -> stopSpinning());
        timeline.playFromStart();
    }

    private void spinSlots() {
        String temp1_1 = slot1_1.getText();
        String temp1_2 = slot1_2.getText();
        String temp1_3 = slot1_3.getText();

        slot3_1.setText(slot2_1.getText());
        slot3_2.setText(slot2_2.getText());
        slot3_3.setText(slot2_3.getText());

        slot2_1.setText(temp1_1);
        slot2_2.setText(temp1_2);
        slot2_3.setText(temp1_3);

        updateFirstRow();
    }

    private void stopSpinning() {
        System.out.println("Spinning stopped!");
    }

    private void updateFirstRow() {
        slot1_1.setText(randomSymbol());
        slot1_2.setText(randomSymbol());
        slot1_3.setText(randomSymbol());
    }

    private String randomSymbol() {
        return symbols[random.nextInt(symbols.length)];
    }

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);
        Parent signUpRoot = loader.load();
        Stage stage = (Stage) btnSwitchToMain.getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
    }
}
