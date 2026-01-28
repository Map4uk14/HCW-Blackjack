package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final ArrayList<Card> cards = new ArrayList<>();

    public void  addCardToHand(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        int aces = 0;
        for (Card card : cards) {
            score += card.getValue();
            if (card.isAce()) {
                aces++;
            }
            while (score > 21 && aces > 0) {
                score -= 10;
                aces--;
            }
        }
        return score;
    }

    public boolean isBust() {
        return getScore() > 21;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void clear() {
        cards.clear();
    }
}
