package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BlackjackController {

    @FXML
    private ImageView playercard1;

    @FXML
    private ImageView playercard2;

    @FXML
    private ImageView playercard3;

    @FXML
    private ImageView playercard4;

    @FXML
    private ImageView playercard5;

    @FXML
    private ImageView playercard6;

    @FXML
    private ImageView playercard7;

    @FXML
    private ImageView playercard8;

    @FXML
    private ImageView dealercard1;

    @FXML
    private ImageView dealercard2;

    @FXML
    private ImageView dealercard3;

    @FXML
    private ImageView dealercard4;

    @FXML
    private ImageView dealercard5;

    @FXML
    private ImageView dealercard6;

    @FXML
    private ImageView dealercard7;

    @FXML
    private ImageView dealercard8;

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
    private Button btnSwitchToMain;


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
        updateUI();

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

        int numOfPCards = playerCards.size();
        int numOfDCards = dealerCards.size();

        ImageView[] playerPics = {playercard1,playercard2,playercard3,playercard4,playercard5,playercard6,playercard7,playercard8};
        ImageView[] dealerPics = {dealercard1,dealercard2,dealercard3,dealercard4,dealercard5,dealercard6,dealercard7,dealercard8};


        for (int i  = 0; i < playerPics.length; i++)
        {
            playerPics[i].setOpacity(0.0);
            dealerPics[i].setOpacity(0.0);
        }



        for(int i = 0; i < numOfPCards && i < 8; i++)
        {
            String card = playerCards.get(i);
            String imagePath = "/images/cards/" + card.toLowerCase().replace(" ","_") + ".png";
            try
            {
                playerPics[i].setImage(new Image(getClass().getResourceAsStream(imagePath)));
                playerPics[i].setOpacity(1.0);
            } catch (Exception e){
                System.out.println("Hiba a kep megnyitasa kozben!");
            }
        }

        if (standButton.isDisabled()) {
            dealerLabel.setText("Dealer's cards: " + dealerCards);
            for(int i = 0; i < numOfDCards && i < 8; i++)
            {
                String card = dealerCards.get(i);
                String imagePath = "/images/cards/" + card.toLowerCase().replace(" ","_") + ".png";
                try
                {
                    dealerPics[i].setImage(new Image(getClass().getResourceAsStream(imagePath)));
                    dealerPics[i].setOpacity(1.0);
                } catch (Exception e){
                    System.out.println("Hiba a kep megnyitasa kozben!");
                }
            }
        } else {
            dealerLabel.setText("Dealer's cards: " + dealerCards);
            for(int i = 0; i < numOfDCards && i < 8; i++)
            {
                String card = dealerCards.get(i);
                String imagePath = "/images/cards/" + card.toLowerCase().replace(" ","_") + ".png";
                try
                {
                    dealerPics[i].setImage(new Image(getClass().getResourceAsStream(imagePath)));
                    dealerPics[i].setOpacity(1.0);
                } catch (Exception e){
                    System.out.println("Hiba a kep megnyitasa kozben!");
                }
            }
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

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);
        Parent mainPage = loader.load();
        Stage stage = (Stage) btnSwitchToMain.getScene().getWindow();
        stage.setScene(new Scene(mainPage));
    }
}