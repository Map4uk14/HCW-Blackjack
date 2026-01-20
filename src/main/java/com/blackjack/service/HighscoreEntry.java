package com.blackjack.service;

public class HighscoreEntry {
    // Der Name des Spielers
    public String name;

    // Die erreichte Punktzahl
    public int score;

    // Konstruktor: Erstellt einen neuen Eintrag
    public HighscoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
}