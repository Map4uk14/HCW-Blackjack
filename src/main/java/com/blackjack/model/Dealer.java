package com.blackjack.model;

public class Dealer {

    private final Hand hand = new Hand();
    public int score;

    public int getScore() {
        return score;
    }

    public Hand getHand() {
        return  hand;
    }
}
