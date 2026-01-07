package com.blackjack.service;

import com.blackjack.model.Dealer;
import com.blackjack.model.Player;

public class SettlementService {

    public void settle(Player player, Dealer dealer) {
        int p = player.getScore();
        int d = dealer.getScore();

        if (p > 21) {
            player.loseBet();
        } else if (d > 21 || p > d) {
            player.winBet();
        } else if (p < d) {
            player.loseBet();
        } else {
            player.draw();
        }
    }
}

