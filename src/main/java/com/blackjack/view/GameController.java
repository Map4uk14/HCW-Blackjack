package com.blackjack.view;

import com.blackjack.controller.BlackjackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
//This is main game controller i.e. after pressing start button
public class GameController {

    @FXML
    public void onBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("menu-view.fxml")
        );
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private final BlackjackController game = new BlackjackController();

    @FXML
    public void onHit() {
        game.playerHit();
        updateUI();
    }

    @FXML
    public void onStand() {
        game.playerStand();
        updateUI();
    }

    @FXML
    public void onDoubleDown() {
        game.playerDoubleDown();
        updateUI();
    }

    private void updateUI() {
        // später aktualisieren für:
        // - Spieler Score
        // - Dealer Score
        // - Credits
        // - Kartenanzeige
        System.out.println("Player Score: " + game.getPlayer().getScore());
        System.out.println("Dealer Score: " + game.getDealer().getScore());
        System.out.println("Credits: " + game.getPlayer().getCredits());
    }

    @FXML private Label playerScoreLabel;
    @FXML private Label dealerScoreLabel;
    @FXML private Label creditsLabel;

    @FXML
    public void onStartRound() {
        game.startRound(100);
        updateUI();
    }
}