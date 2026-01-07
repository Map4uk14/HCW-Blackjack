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

    public void draw() {

    }
}
