package com.blackjack.view;

import com.blackjack.service.HighscoreEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class HighscoreController {
    @FXML private VBox highscoreList; // reference from the FXML

    @FXML
    public void initialize() {
        highscoreList.getChildren().clear();
        try {
            // Liest die Datei direkt ein
            List<String> lines = Files.readAllLines(Paths.get("scores.txt"));
            List<HighscoreEntry> entries = new ArrayList<>();

            for (String line : lines) {
                String[] p = line.split(":");
                entries.add(new HighscoreEntry(p[0], Integer.parseInt(p[1])));
            }

            // Sortieren: Höchste Zahl zuerst
            entries.sort((a, b) -> b.score - a.score);

            // Anzeigen (Top 5)
            for (int i = 0; i < Math.min(5, entries.size()); i++) {
                HighscoreEntry e = entries.get(i);
                Label l = new Label((i + 1) + ". " + e.name + ": " + e.score + "€");
                l.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
                highscoreList.getChildren().add(l);
            }
        } catch (Exception e) {
            highscoreList.getChildren().add(new Label("Noch keine Einträge..."));
        }
    }

    @FXML
    public void onBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
