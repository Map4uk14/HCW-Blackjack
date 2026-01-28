package com.blackjack.model;

public class Card {
    // Aufzählung der Kartenfarben
    public enum Suit { HEART, DIAMOND, CLUB, SPADE }

    // Aufzählung der Ränge mit den jeweiligen Blackjack-Werten
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        public final int value;
        Rank(int value) { this.value = value; }
    }

    private final Suit suit;
    private final Rank rank;

    // Konstruktor zur Erstellung einer Karte
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Gibt den Punktwert der Karte zurück
    public int getValue() { return rank.value; }

    // Prüft, ob die Karte ein Ass ist
    public boolean isAce() { return rank == Rank.ACE; }

    // Gibt den Namen der Farbe für die Bild-Ressourcen zurück
    public String getSuitName() {
        if (suit == Suit.HEART) return "Hearts";
        if (suit == Suit.DIAMOND) return "Diamond";
        if (suit == Suit.CLUB) return "Clubs";
        return "Spades";
    }

    // Gibt den Namen des Rangs für die Bild-Dateinamen zurück
    public String getRankName() {
        if (rank == Rank.ACE) return "1";
        if (rank == Rank.JACK) return "Junge";
        if (rank == Rank.QUEEN) return "Queen";
        if (rank == Rank.KING) return "King";
        return String.valueOf(rank.value);
    }
}