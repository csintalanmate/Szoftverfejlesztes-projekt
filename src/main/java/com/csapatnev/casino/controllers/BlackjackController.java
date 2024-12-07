package com.csapatnev.casino.controllers;

import com.csapatnev.casino.BlackjackApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BlackjackController {

    @FXML
    private Label playerLabel;

    @FXML
    private Label dealerLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private Button hitButton;

    @FXML
    private Button standButton;

    @FXML
    private Label playerVal;

    @FXML
    private Label dealerVal;


    private List<String> deck;
    private List<String> playerCards;
    private List<String> dealerCards;

    // FXML inicializáló metódus
    @FXML
    public void initialize() {
        handleNewGame();
    }

    // Új játék indítása
    @FXML
    public void handleNewGame() {
        deck = initializeDeck();
        Collections.shuffle(deck);
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();

        playerCards.add(deck.remove(0));
        playerCards.add(deck.remove(0));
        dealerCards.add(deck.remove(0));
        dealerCards.add(deck.remove(0));

        updateUI();
        resultLabel.setText("");
        hitButton.setDisable(false);
        standButton.setDisable(false);
    }

    // Játékos új lapot kér
    @FXML
    public void handleHit() {
        playerCards.add(deck.remove(0));
        updateUI();

        if (calculateHandValue(playerCards) > 21) {
            resultLabel.setText("Player Busted! Dealer Wins!");
            hitButton.setDisable(true);
            standButton.setDisable(true);
        }
    }

    // Játékos megáll
    @FXML
    public void handleStand() {
        while (calculateHandValue(dealerCards) <= 17 || calculateHandValue(dealerCards) <= calculateHandValue(playerCards)) {
            dealerCards.add(deck.remove(0));
        }
        updateUI();
        determineWinner();
        hitButton.setDisable(true);
        standButton.setDisable(true);
    }

    // Pakli inicializálása
    private List<String> initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        List<String> deck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }

    // Kéz értékének kiszámítása
    private int calculateHandValue(List<String> hand) {
        int value = 0;
        int aceCount = 0;
        for (String card : hand) {
            String rank = card.split(" ")[0];
            switch (rank) {
                case "Ace":
                    aceCount++;
                    value += 11;
                    break;
                case "King":
                case "Queen":
                case "Jack":
                    value += 10;
                    break;
                default:
                    value += Integer.parseInt(rank);
            }
        }
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }

    // UI frissítése
    private void updateUI() {
        playerLabel.setText("Player's cards: " + playerCards);
        if (standButton.isDisabled()) {
            dealerLabel.setText("Dealer's cards: " + dealerCards);
        } else {
            dealerLabel.setText("Dealer's cards: " + dealerCards);
        }
    }

    // Győztes meghatározása
    private void determineWinner() {
        int playerValue = calculateHandValue(playerCards);
        int dealerValue = calculateHandValue(dealerCards);

        if (playerValue > 21) {
            resultLabel.setText("Dealer Wins!");
        } else if (dealerValue > 21) {
            resultLabel.setText("Player Wins!");
        } else if (playerValue == dealerValue) {
            resultLabel.setText("It's a Tie!");
        } else {
            resultLabel.setText(playerValue > dealerValue ? "Player Wins!" : "Dealer Wins!");
        }
    }
}