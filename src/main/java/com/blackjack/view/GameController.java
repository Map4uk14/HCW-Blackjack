package com.blackjack.view;

import com.blackjack.controller.BlackjackController;
import com.blackjack.model.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController {
    // UI-Texte für Punkte und Geld
    @FXML private Label playerScoreLabel, dealerScoreLabel, creditsLabel;
    @FXML private Label ai1Score, ai2Score, ai3Score;
    @FXML private Label ai1Credits, ai2Credits, ai3Credits;
    @FXML private Label resultLabel;

    // Boxen für die Kartenanzeige (HBox = Reihe, StackPane = Stapel)
    @FXML private HBox playerCardBox, dealerCardBox;
    @FXML private StackPane ai1CardBox, ai2CardBox, ai3CardBox;

    // Eingabefeld und Knöpfe
    @FXML private TextField betField;
    @FXML private Button hitBtn, standBtn, doubleBtn, dealBtn, cashoutBtn;

    // Verbindung zur Spiellogik
    private final BlackjackController game = new BlackjackController();

    // Startet die Runde
    @FXML
    public void onStartRound() {
        try {
            int bet = Integer.parseInt(betField.getText());
            if (bet > game.getPlayer().getCredits()) {
                resultLabel.setText("Zu wenig Geld!");
                return;
            }
            if (bet >= 10) {
                game.startRound(bet);
                updateUI(); // Oberfläche aktualisieren
            } else {
                resultLabel.setText("Min. 10€ setzen!");
            }
        } catch (Exception e) { resultLabel.setText("Zahl eingeben!"); }
    }

    // Spieler-Aktionen (Hit, Stand, Double)
    @FXML public void onHit() { game.playerHit(); updateUI(); }
    @FXML public void onStand() { game.playerStand(); updateUI(); }
    @FXML public void onDoubleDown() { game.playerDoubleDown(); updateUI(); }
    @FXML public void onCashOut(ActionEvent event) throws IOException { onBack(event); }

    // Aktualisiert alle Texte und Bilder auf dem Bildschirm
    private void updateUI() {
        boolean playerIsActive = game.isPlayerTurn();

        // Aktuelle Werte aus der Logik holen und anzeigen
        playerScoreLabel.setText("Hand: " + game.getPlayer().getScore());
        creditsLabel.setText("Guthaben: " + game.getPlayer().getCredits() + "€");
        resultLabel.setText(game.getResultText());

        // KI-Spieler einzeln updaten
        updateAiDisplay(0, ai1Score, ai1Credits, ai1CardBox);
        updateAiDisplay(1, ai2Score, ai2Credits, ai2CardBox);
        updateAiDisplay(2, ai3Score, ai3Credits, ai3CardBox);

        // Dealer-Punkte zeigen (oder verstecken)
        dealerScoreLabel.setText(playerIsActive ? "Dealer: ?" : "Dealer: " + game.getDealer().getScore());

        // Knöpfe sperren/entsperren je nach Spielphase
        dealBtn.setDisable(playerIsActive);
        hitBtn.setDisable(!playerIsActive || game.getPlayer().getScore() >= 21);
        standBtn.setDisable(!playerIsActive);
        doubleBtn.setDisable(!playerIsActive || game.getPlayer().getHand().getCards().size() > 2);

        // Bilder für Spieler und Dealer neu zeichnen
        renderPlayerCards(playerCardBox, game.getPlayer().getHand().getCards(), false);
        renderPlayerCards(dealerCardBox, game.getDealer().getHand().getCards(), playerIsActive);
    }

    // Hilfsmethode für die KI-Anzeige
    private void updateAiDisplay(int index, Label scoreLabel, Label creditsLabel, StackPane cardBox) {
        var ai = game.getAiPlayers().get(index);
        if (ai.getCredits() <= 0 || !game.isAiActive(index)) {
            scoreLabel.setText("RAUS");
            creditsLabel.setText("0€");
            cardBox.getChildren().clear();
        } else {
            scoreLabel.setText("Score: " + ai.getScore());
            creditsLabel.setText(ai.getCredits() + "€");
            renderAiCards(cardBox, ai.getHand().getCards());
        }
    }

    // Zeichnet Karten nebeneinander (Spieler/Dealer)
    private void renderPlayerCards(HBox box, java.util.List<Card> cards, boolean isHidden) {
        box.getChildren().clear(); // Box vorher leeren
        for (int i = 0; i < cards.size(); i++) {
            // Wenn erste Karte vom Dealer: Back.png nutzen
            String fileName = (isHidden && i == 0) ? "Back.png" : cards.get(i).getSuitName() + "_" + cards.get(i).getRankName() + ".png";

            try {
                // Bild aus dem Ordner laden
                var imgStream = getClass().getResourceAsStream("/cards/" + fileName);
                if (imgStream != null) {
                    ImageView cardImage = new ImageView(new Image(imgStream));
                    cardImage.setFitHeight(130); // Höhe festlegen
                    cardImage.setPreserveRatio(true); // Seitenverhältnis beibehalten
                    box.getChildren().add(cardImage);
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // Zeichnet Karten als Stapel (KI)
    private void renderAiCards(StackPane pane, java.util.List<Card> cards) {
        pane.getChildren().clear();
        int offset = 0;
        for (Card card : cards) {
            String fileName = card.getSuitName() + "_" + card.getRankName() + ".png";
            try {
                var imgStream = getClass().getResourceAsStream("/cards/" + fileName);
                if (imgStream != null) {
                    ImageView cardImage = new ImageView(new Image(imgStream));
                    cardImage.setFitHeight(70); // Kleinere Karten für KI
                    cardImage.setPreserveRatio(true);
                    cardImage.setTranslateY(offset); // Karte ein Stück nach unten schieben
                    cardImage.setTranslateX(offset * 0.5); // Karte ein Stück nach rechts schieben
                    pane.getChildren().add(cardImage);
                    offset += 15; // Versatz für die nächste Karte erhöhen
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // Zurück zum Hauptmenü
    @FXML
    public void onBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}