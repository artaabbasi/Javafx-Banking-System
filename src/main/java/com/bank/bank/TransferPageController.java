package com.bank.bank;

import com.bank.DB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class TransferPageController {
    @FXML
    private Button transferShabaButon;
    @FXML
    private Button transferTransferButon;
    @FXML
    private TextField transferShabaField;
    @FXML
    private TextField transferTransferField;
    @FXML
    private TextField transferAmountField;
    @FXML
    private Label transferMessageLabel;
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
    public void onTransferButton(ActionEvent clickEvent) throws IOException {
        String transfercode = transferTransferField.getText();
        FileManager fm = new FileManager();
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        DB db = fm.read_object();
        UserBank localuserBank = new UserBank();
        boolean flag = true;
        int amount = 0;
        try{
            amount = Integer.parseInt(transferAmountField.getText());
        } catch(NumberFormatException ex) {
        }
        if (amount > 0) {
            for (UserBank userBank : db.user_banks) {
                if (userBank.transfer_code.equals(transfercode)) {
                    if (userBank.bank.name.equals(session.bank.name)) {
                        localuserBank = userBank;
                    }else{
                        transferMessageLabel.setTextFill(Color.web("#F5130C"));
                        transferMessageLabel.setText("cant transfer money to another bank with transfer code test shaba");
                        transferTransferField.clear();
                        flag = false;
                    }
                }
            }
            if (session.userBank.balance >= amount && flag) {
                session.userBank.balance -= amount;
                localuserBank.balance += amount;
                session.userBank.save();
                localuserBank.save();
                session.save();
                transferTransferField.clear();
                transferAmountField.clear();
                transferShabaField.clear();
                transferMessageLabel.setTextFill(Color.web("#5DF50C"));
                transferMessageLabel.setText("You transferd successfully!");
                Logger logger = new Logger();
                logger.logger("transfer from transfer code: " + localuserBank.transfer_code +" user: " + session.user.username + " Transfer: " + session.userBank.transfer_code + " Amount: " + transferAmountField.getText());
                logger.logger("User balance increased: user: " + localuserBank.user.username + " Amount: " +  Integer.toString(amount));
                logger.logger("User balance decreased: user: " + session.user.username + " Amount: " +  Integer.toString(amount));
            } else {
                transferMessageLabel.setTextFill(Color.web("#F5130C"));
                transferMessageLabel.setText("you dont have enough money in your account!");
                transferAmountField.clear();
            }
        }

    }
    public void onShabaButton(ActionEvent clickEvent) throws IOException {
        String shaba_code = transferShabaField.getText();
        FileManager fm = new FileManager();
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        DB db = fm.read_object();
        UserBank localuserBank = new UserBank();
        int amount = 0;
        try{
            amount = Integer.parseInt(transferAmountField.getText());
        } catch(NumberFormatException ex) {
        }
        if (amount > 0) {
            for (UserBank userBank : db.user_banks) {
                if (!(userBank.shaba_code == null)) {
                    if (userBank.shaba_code.equals(shaba_code)) {
                        localuserBank = userBank;
                    }
                }
            }
            if (session.userBank.balance >= amount) {
                session.userBank.balance -= amount;
                localuserBank.balance += amount;
                session.userBank.save();
                localuserBank.save();
                session.save();
                transferTransferField.clear();
                transferAmountField.clear();
                transferShabaField.clear();
                transferMessageLabel.setTextFill(Color.web("#5DF50C"));
                transferMessageLabel.setText("You transferd successfully!");
                Logger logger = new Logger();
                logger.logger("transfer from shaba code: " + localuserBank.shaba_code +" user: " + session.user.username + " Shaba: " + session.userBank.shaba_code + " Amount: " + Integer.toString(amount));
                logger.logger("User balance increased: user: " + localuserBank.user.username + " Amount: " +  Integer.toString(amount));
                logger.logger("User balance decreased: user: " + session.user.username + " Amount: " +  Integer.toString(amount));
            } else {
                transferMessageLabel.setTextFill(Color.web("#F5130C"));
                transferMessageLabel.setText("you dont have enough money in your account!");
                transferAmountField.clear();
            }
        }
    }
}
