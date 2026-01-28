package com.blackjack.model;

public class Player {

    private final Hand hand = new Hand();
    private int credits;
    private int currentBet;

    public Player(int credits) {
        this.credits = credits;
    }

    public void winBet() {
        credits += currentBet * 2 ;
        System.out.println("Player won the bet: " + credits + "+" + currentBet);
    }

    public void loseBet() {
        currentBet = 0;
        System.out.println("Player lost the bet: " + credits + "-" + currentBet);
    }

    public int getScore() {
        return hand.getScore();
    }

    public Hand getHand() {
        return hand;
    }

    // Draw() changed by Lukas
    public void hit(Deck deck) {
        hand.addCardToHand(deck.draw());
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean placeBet(int bet) {
        if (bet > 0 && bet <= credits) {
            currentBet = bet;
            credits -= bet;
            return true;
        }
        return false;
    }

    public int getCredits() {
        return credits;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void pushBet() {
        credits += currentBet;       // Einsatz zurück
        currentBet = 0;
    }
}