package com.bank.bank;

import com.bank.DB.Loan;
import com.bank.DB.Logger;
import com.bank.DB.Session;
import com.bank.DB.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class RequestLoanController {
    @FXML
    private TextField loanAmountField;
    @FXML
    private Button loanSubmitButton;
    @FXML
    private Label loanMessageLabel;
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

    public void onSubmitButton() throws IOException {
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        int amount = 0;
        try{
            amount = Integer.parseInt(loanAmountField.getText());
            if(!(amount > 0)) {
                loanMessageLabel.setTextFill(Color.web("#F5130C"));
                loanMessageLabel.setText("You should enter amount!");
            }else {
                Loan loan = new Loan();
                loan.userBank = session.userBank;
                loan.user = session.user;
                loan.bank = session.bank;
                loan.approved = false;
                loan.amount = amount;
                loan.store();
                loanMessageLabel.setTextFill(Color.web("#5DF50C"));
                loanMessageLabel.setText("You requested successfully!");
                Logger logger = new Logger();
                logger.logger("New loan requested: user: " + session.user.username + " amount: "+ amount );
            }
        } catch(NumberFormatException ex) {
            loanMessageLabel.setTextFill(Color.web("#F5130C"));
            loanMessageLabel.setText("You should enter numeric amount!");
        }
    }
}
