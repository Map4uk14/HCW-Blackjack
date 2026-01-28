package com.blackjack.logic;

import com.blackjack.model.Dealer;
import com.blackjack.model.Deck;
import com.blackjack.model.Hand;

public class DealerAI {

    public void play(Dealer dealer, Deck deck) {
        while (dealer.getHand().getScore() < 17) {
            dealer.getHand().addCardToHand(deck.draw());
            //String for testing DealerAI logic / lazy to write Tests
            System.out.println(dealer.getHand().getScore());
        }
    }
}
