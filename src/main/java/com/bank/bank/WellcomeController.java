package com.bank.bank;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WellcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}