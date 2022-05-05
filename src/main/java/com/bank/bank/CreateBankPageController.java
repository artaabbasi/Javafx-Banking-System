package com.bank.bank;

import com.bank.DB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class CreateBankPageController {
    @FXML
    private TextField createbankNameField;
    @FXML
    private Button createbankCreateButton;
    @FXML
    private Label createbankMessageLable;
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
    public void onBankCreateButton(ActionEvent clickEvent) throws IOException, InterruptedException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        String name = createbankNameField.getText();
        Boolean flag = true;
        for (Bank bank : db.banks) {
            if (bank.name.equals(name)) {
                createbankMessageLable.setTextFill(Color.web("#F5130C"));
                createbankMessageLable.setText("There is a bank with this name!");
                createbankNameField.clear();
                flag = false;
            }
        }
        if (name.length() < 1){
            createbankMessageLable.setTextFill(Color.web("#F5130C"));
            createbankMessageLable.setText("Enter banks name!");
            createbankNameField.clear();
            flag = false;
        }
        if (flag) {
            Bank bank = new Bank(name);
            SessionManager sm = new SessionManager();
            Session session = sm.read_object();
            session.bank = bank;
            UserBank userBank = new UserBank(session.user, session.bank, true);
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
            userBank.shaba_code = shaba;
            userBank.transfer_code = transfer;
            userBank.is_approved = true;
            createbankMessageLable.setTextFill(Color.web("#5DF50C"));
            createbankMessageLable.setText("You created bank successfully!");
            session.save();
            bank.store();
            userBank.store();
            Logger logger = new Logger();
            logger.logger("New Bank created: name: " + bank.name);
            logger.logger("userbank created: user:" + session.user.username + " Shaba: "+ userBank.shaba_code + " Transfer: " + userBank.transfer_code + " **ADMIN**");
            BankApplication scene = new BankApplication();
            scene.onChangeScene("choose_bank_page.fxml");
        }
    }
}
