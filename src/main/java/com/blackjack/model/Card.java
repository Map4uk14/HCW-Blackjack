package com.blackjack.model;

public class Card {

    public enum Suit { HEART, DIAMOND, CLUB, SPADE }
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        public final int value;
        Rank(int value) { this.value = value; }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        return rank.value;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }
}
