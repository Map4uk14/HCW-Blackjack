package com.blackjack.view;

import com.blackjack.logic.DealerAI;
import com.blackjack.model.Dealer;
import com.blackjack.model.Deck;
import com.blackjack.model.Hand;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;
//Start of the app screen
public class BlackjackApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("username-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("BlackJack 21");
        stage.getIcons().add(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/blackjack/view/icon.png")))
        );
        stage.setScene(scene);
        stage.show();

        //Testing DealerAI Logic
        System.out.println("Dealer draws card value:");
        DealerAI dealer = new DealerAI();
        Dealer dealer2 = new Dealer();
        Deck dealerHand = new Deck();
        dealer.play(dealer2, dealerHand);
    }

    public static void main(String[] args) {
        launch();
    }

}