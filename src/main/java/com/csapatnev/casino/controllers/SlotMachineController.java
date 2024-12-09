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

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class SlotMachineController {
    @FXML
    private Label slot1_1, slot1_2, slot1_3;
    @FXML
    private Label slot2_1, slot2_2, slot2_3;
    @FXML
    private Label slot3_1, slot3_2, slot3_3;

    @FXML
    private Button btnSwitchToMain;
    @FXML
    private Label resultLabel;

    @FXML
    private Label winCounterLabel;
    @FXML
    private Label lossCounterLabel;

    private int wins;
    private int losses;

    private final Random random = new Random();
    private final String[] symbols = {"🍒", "🍋", "🍊", "🍉", "⭐", "🔔"};
    private Timeline timeline;

    public SlotMachineController() {
    }

    @FXML
    public void initialize() {
        wins = 0;
        losses = 0;
        updateFirstRow();
        resultLabel.setText("");
        updateCounters(); // Initialize counters display
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

        resultLabel.setText("");
    }

    private void spinSlots() {
        resetLabelStyles();

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
        checkForWin();
    }

    private void updateFirstRow() {
        slot1_1.setText(randomSymbol());
        slot1_2.setText(randomSymbol());
        slot1_3.setText(randomSymbol());
    }

    private String randomSymbol() {
        return symbols[random.nextInt(symbols.length)];
    }

    private void checkForWin() {
        boolean hasWin = checkHorizontalWin(slot1_1, slot1_2, slot1_3) ||
                checkHorizontalWin(slot2_1, slot2_2, slot2_3) ||
                checkHorizontalWin(slot3_1, slot3_2, slot3_3) ||
                checkDiagonalWin();

        if (hasWin) {
            resultLabel.setText("You Win!");
            highlightWinningLabels();
            wins++;
        } else {
            resultLabel.setText("You Lose!");
            losses++;
        }

        updateCounters(); // Update the displayed counters after each spin
    }

    private boolean checkHorizontalWin(Label label1, Label label2, Label label3) {
        return label1.getText().equals(label2.getText()) && label2.getText().equals(label3.getText());
    }

    private boolean checkDiagonalWin() {
        return (slot1_1.getText().equals(slot2_2.getText()) &&
                slot2_2.getText().equals(slot3_3.getText())) ||
                (slot1_3.getText().equals(slot2_2.getText()) &&
                        slot2_2.getText().equals(slot3_1.getText()));
    }

    private void highlightWinningLabels() {
        if (checkHorizontalWin(slot1_1, slot1_2, slot1_3)) {
            highlightLabel(slot1_1);
            highlightLabel(slot1_2);
            highlightLabel(slot1_3);
        }

        if (checkHorizontalWin(slot2_1, slot2_2, slot2_3)) {
            highlightLabel(slot2_1);
            highlightLabel(slot2_2);
            highlightLabel(slot2_3);
        }

        if (checkHorizontalWin(slot3_1, slot3_2, slot3_3)) {
            highlightLabel(slot3_1);
            highlightLabel(slot3_2);
            highlightLabel(slot3_3);
        }

        if (checkDiagonalWin()) {
            if (slot1_1.getText().equals(slot2_2.getText()) &&
                    slot2_2.getText().equals(slot3_3.getText())) {
                highlightLabel(slot1_1);
                highlightLabel(slot2_2);
                highlightLabel(slot3_3);
            }

            if (slot1_3.getText().equals(slot2_2.getText()) &&
                    slot2_2.getText().equals(slot3_1.getText())) {
                highlightLabel(slot1_3);
                highlightLabel(slot2_2);
                highlightLabel(slot3_1);
            }
        }
    }

    private void resetLabelStyles() {
        resetStyleForLabel(slot1_1);
        resetStyleForLabel(slot1_2);
        resetStyleForLabel(slot1_3);

        resetStyleForLabel(slot2_1);
        resetStyleForLabel(slot2_2);
        resetStyleForLabel(slot2_3);

        resetStyleForLabel(slot3_1);
        resetStyleForLabel(slot3_2);
        resetStyleForLabel(slot3_3);
    }

    private void resetStyleForLabel(Label label) {
        label.setStyle("-fx-font-size: 100; -fx-border-color: black; -fx-border-width: 5; -fx-background-color: white; -fx-padding: 10;");
    }

    private void highlightLabel(Label label) {
        label.setStyle("-fx-font-size: 100; -fx-border-color: black; -fx-border-width: 5; -fx-background-color: yellow; -fx-padding: 10;");
    }

    public void updateCounters() {
        winCounterLabel.setText("Wins: " + wins);
        lossCounterLabel.setText("Losses: " + losses);
    }


    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);
        Parent mainPage = loader.load();
        Stage stage = (Stage) btnSwitchToMain.getScene().getWindow();
        stage.setScene(new Scene(mainPage));
    }
}