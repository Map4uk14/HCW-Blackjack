package com.blackjack.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class SettingsController {

    @FXML
    private Button volumeButton;

    private int volume = 0;

    private final String SETTINGS_FILE = "settings.properties";

    @FXML
    public void initialize() {
        loadVolume();
        updateButton();
    }

    @FXML
    private void toggleVolume() {
        volume += 25;
        if (volume > 100) volume = 0;

        updateButton();
        saveVolume();
    }

    private void updateButton() {
        volumeButton.setText("Volume: " + volume + "%");
    }

    private void saveVolume() {
        try (OutputStream out = new FileOutputStream(SETTINGS_FILE)) {
            Properties props = new Properties();
            props.setProperty("volume", String.valueOf(volume));
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadVolume() {
        try (InputStream in = new FileInputStream(SETTINGS_FILE)) {
            Properties props = new Properties();
            props.load(in);
            volume = Integer.parseInt(props.getProperty("volume", "0"));
        } catch (IOException e) {
            volume = 0;
        }
    }

    @FXML
    public void onBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("menu-view.fxml")
        );
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
