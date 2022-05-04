package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class UserBankCreatePageController {
    @FXML
    private TextField ubcreateDepositField;
    @FXML
    private Button CreateSubmitButton;
    @FXML
    private Button backButton;
    public void back() throws IOException {
        BankApplication scene = new BankApplication();
        try {
            scene.onChangeScene("choose_bank_page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void onCreateSubmitButton(ActionEvent clickEvent) throws IOException {
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        session.userBank.balance = Integer.parseInt(ubcreateDepositField.getText());
        Random rand = new Random();
        String shaba = Integer.toString(abs(rand.nextInt()));
        String transfer = Integer.toString(abs(rand.nextInt()));
        for (UserBank userBank1 : db.user_banks) {
            if (shaba == userBank1.shaba_code) {
                shaba = Integer.toString(rand.nextInt());
            }
            if (transfer == userBank1.transfer_code) {
                transfer = Integer.toString(rand.nextInt());
            }
        }
        session.userBank.shaba_code = shaba;
        session.userBank.transfer_code = transfer;
        session.userBank.save();
        session.save();
        BankApplication scene = new BankApplication();
        try {
            scene.onChangeScene("choose_bank_page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
