package com.blackjack.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class UsernameController {

    @FXML
    public Label errorLabel;
    public TextField usernameField;

    @FXML
    private void onContinue(ActionEvent event) throws IOException {
        String username = usernameField.getText().trim();
        if(!isValidUsername(username)) {
            showError("Username must be at least 3 and max 20 characters/numbers");
            return;
        }

        errorLabel.setVisible(false);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/blackjack/view/menu-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }


    private boolean isValidUsername(String username) {
        if (username.isEmpty()) return false;
        if (username.length() < 3 || username.length() > 20) return false;
        return username.matches("[a-zA-Z0-9]+");
    }


    @FXML
    public void onExit(ActionEvent event) {
        Platform.exit();
    }
}
