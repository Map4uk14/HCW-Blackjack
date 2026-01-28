package com.blackjack.model;

public class Dealer {

    private final Hand hand = new Hand();
    public int score;

    public int getScore() {
        return hand.getScore();
    }

    public Hand getHand() {
        return  hand;
    }

    public boolean isBust() {
        return hand.isBust();
    }
}