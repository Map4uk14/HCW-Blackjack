package com.blackjack.controller;


import com.blackjack.logic.DealerAI;
import com.blackjack.model.*;
import com.blackjack.service.SettlementService;

public class BlackjackController {

    private Deck deck;
    private final Player player = new Player(1000);
    private final Dealer dealer = new Dealer();

    private final DealerAI dealerAI = new DealerAI();
    private final SettlementService settlementService = new SettlementService();

    private boolean playerTurn;
    private boolean doubleDownAllowed;

    // ROUND START
    public void startRound(int bet) {
        deck = new Deck();

        player.getHand().getCards().clear(); // optional falls Reset fehlt
        dealer.getHand().getCards().clear();

        player.placeBet(bet);

        // Initial deal
        player.hit(deck);
        player.hit(deck);

        dealer.getHand().addCardToHand(deck.draw());
        dealer.getHand().addCardToHand(deck.draw());

        playerTurn = true;
        doubleDownAllowed = true;
    }

    // HIT
    public void playerHit() {
        if (!playerTurn) return;

        player.hit(deck);
        doubleDownAllowed = false;

        if (player.isBust()) {
            endRound();
        }
    }

    // STAND
    public void playerStand() {
        if (!playerTurn) return;
        endRound();
    }

    // DOUBLE DOWN
    public void playerDoubleDown() {
        if (!playerTurn || !doubleDownAllowed) return;

        // Einsatz verdoppeln
        player.placeBet(player.getCurrentBet());

        // genau eine Karte
        player.hit(deck);

        endRound();
    }

    // ROUND END
    private void endRound() {
        playerTurn = false;

        if (!player.isBust()) {
            dealerAI.play(dealer, deck);
        }

        settlementService.settle(player, dealer);
    }

    // GETTER für UI
    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public boolean isDoubleDownAllowed() {
        return doubleDownAllowed;
    }
}
