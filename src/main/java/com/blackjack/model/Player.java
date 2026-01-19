package com.blackjack.model;

public class Player {



    private final Hand hand = new Hand();
    private int credits;
    private int currentBet;

    public Player(int credits) {
        this.credits = credits;
    }

    public void winBet() {
        credits += currentBet ;
        System.out.println("Player won the bet: " + credits + currentBet);
    }

    public void loseBet() {
        credits -= currentBet;
        System.out.println("Player lost the bet: " + credits + currentBet);
    }

    public int getScore() {
        return credits;
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

    public void placeBet(int bet) {
    }

    public int getCredits() {
        return credits;
    }

    public boolean isBust() {
        return hand.isBust();
    }
}