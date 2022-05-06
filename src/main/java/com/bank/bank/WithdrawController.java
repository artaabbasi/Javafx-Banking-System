package com.bank.bank;

import com.bank.DB.Logger;
import com.bank.DB.Session;
import com.bank.DB.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class WithdrawController {
    @FXML
    private Button withdrawButton;
    @FXML
    private TextField withdrawAmountField;
    @FXML
    private Label withdrawMessageLabel;

    public void onWithdrawButton() throws IOException {
        int amount = 0;
        try{
            amount = Integer.parseInt(withdrawAmountField.getText());
        } catch(NumberFormatException ex) {
            withdrawMessageLabel.setTextFill(Color.web("#F5130C"));
            withdrawMessageLabel.setText("You should enter numeric amount!");
        }
        if(amount > 0) {
            withdrawMessageLabel.setTextFill(Color.web("#F5130C"));
            withdrawMessageLabel.setText("You should enter amount!");
        }
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        if(session.userBank.balance >=amount) {
            session.userBank.balance -= amount;
            session.userBank.save();
            session.save();
            withdrawMessageLabel.setTextFill(Color.web("#5DF50C"));
            withdrawMessageLabel.setText("You withdraw successfully!");
            Logger logger = new Logger();
            logger.logger("Withdraw done: user: " + session.userBank.user.username + " bank: " + session.userBank.bank.name + " amount: " + Integer.toString(amount));
            logger.logger("User balance decreased: user: " + session.user.username + " Amount: " + Integer.toString(amount));
        }else {
            withdrawMessageLabel.setTextFill(Color.web("#F5130C"));
            withdrawMessageLabel.setText("You dont have enough money!");
        }
    }





    @FXML
    private Button backButton;
    public void back() throws IOException {
        BankApplication scene = new BankApplication();
        try {
            scene.onChangeScene("customer_bank_page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
