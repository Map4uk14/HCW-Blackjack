package com.blackjack.controller;

import com.blackjack.logic.DealerAI;
import com.blackjack.model.*;
import com.blackjack.service.SettlementService;
import java.util.ArrayList;

// Haupt-Controller zur Steuerung des Spielablaufs
public class BlackjackController {
    // Grundlegende Spielobjekte und Teilnehmer
    private Deck deck;
    private final Player player = new Player(1000);
    private final Dealer dealer = new Dealer();
    private final ArrayList<Player> aiPlayers = new ArrayList<>();

    // Logik für Dealer-Verhalten und Abrechnung
    private final DealerAI dealerAI = new DealerAI();
    private final SettlementService settlementService = new SettlementService();

    // Status-Variablen für die aktuelle Runde
    private boolean playerTurn = false;
    private String resultText = "";
    private int currentBet = 0;
    private static final int AI_BET = 50; // Festgelegter Einsatz für KI-Spieler

    public BlackjackController() {
        // Initialisiert 3 KI-Gegner mit Standard-Guthaben
        aiPlayers.add(new Player(1000));
        aiPlayers.add(new Player(1000));
        aiPlayers.add(new Player(1000));
    }

    // Setzt die Runde zurück und teilt erste Karten aus
    public void startRound(int bet) {
        deck = new Deck(); // Neues Deck für jede Runde
        resultText = "";
        currentBet = bet;
        player.getHand().clear();
        dealer.getHand().clear();

        // Spieler setzt Einsatz und zieht zwei Karten
        player.placeBet(bet);
        player.hit(deck);
        player.hit(deck);

        // Dealer erhält seine Startkarten
        dealer.getHand().addCardToHand(deck.draw());
        dealer.getHand().addCardToHand(deck.draw());

        // KI-Spieler setzen und ziehen, falls Guthaben vorhanden ist (Regel)
        for (Player ai : aiPlayers) {
            ai.getHand().clear();
            if (ai.getCredits() >= AI_BET) {
                ai.placeBet(AI_BET);
                ai.hit(deck);
                ai.hit(deck);
            }
        }
        playerTurn = true; // Spieler-Interaktion aktivieren
    }

    // Spieler fordert eine weitere Karte an (HIT)
    public void playerHit() {
        if (playerTurn) {
            player.hit(deck);
            // Wenn der Spieler über 21 kommt, endet die Runde sofort
            if (player.isBust()) endRound();
        }
    }

    // Spieler beendet seinen Zug (STAND)
    public void playerStand() {
        if (playerTurn) endRound();
    }

    // Einsatz verdoppeln, eine letzte Karte ziehen und beenden
    public void playerDoubleDown() {
        if (playerTurn) {
            player.placeBet(player.getCurrentBet());
            currentBet *= 2;
            player.hit(deck);
            endRound();
        }
    }

    // Rundenende: Züge der KI/Dealer und Gewinnermittlung
    private void endRound() {
        playerTurn = false;

        // KI spielt automatisch bis mindestens 17 Punkte erreicht sind (Gleiche Logik wie Dealer)
        for (Player ai : aiPlayers) {
            if (ai.getCurrentBet() > 0) {
                while (ai.getScore() < 17 && !ai.isBust()) {
                    ai.hit(deck);
                }
            }
        }

        // Dealer-Logik ausführen
        dealerAI.play(dealer, deck);

        // Geld/Score Abrechnung aller Teilnehmer
        int p = player.getScore();
        int d = dealer.getScore();
        settlementService.settle(player, dealer);

        for (Player ai : aiPlayers) {
            if (ai.getCurrentBet() > 0) {
                settlementService.settle(ai, dealer);
            }
        }

        // Ergebnistext für das UI-Feedback
        if (p > 21) resultText = "BUST! -" + currentBet + "€";
        else if (d > 21 || p > d) resultText = "YOU WIN! +" + currentBet + "€";
        else if (p < d) resultText = "YOU LOSE! -" + currentBet + "€";
        else resultText = "PUSH! +0€";
    }

    // Getter-Methoden für den Zugriff aus der View
    public Player getPlayer() { return player; }
    public Dealer getDealer() { return dealer; }
    public ArrayList<Player> getAiPlayers() { return aiPlayers; }
    public boolean isPlayerTurn() { return playerTurn; }
    public String getResultText() { return resultText; }

    // Check, ob eine KI noch genug Credits zum Mitspielen hat
    public boolean isAiActive(int index) {
        if (index < 0 || index >= aiPlayers.size()) return false;
        return aiPlayers.get(index).getCredits() >= AI_BET;
    }
}