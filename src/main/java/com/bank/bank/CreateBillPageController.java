package com.bank.bank;

import com.bank.DB.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class CreateBillPageController {
    @FXML
    private TextField billcreateNameField;
    @FXML
    private TextField billcreateAmountField;
    @FXML
    private TextField billcreateUserField;
    @FXML
    private Button billcreateCreateButton;
    @FXML
    private Label billcreateMessageLabel;
    @FXML
    private Button backButton;
    public void back() throws IOException {
        BankApplication scene = new BankApplication();
        try {
            scene.onChangeScene("admin_page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onCreateButton() throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        SessionManager sm = new SessionManager();
        if (billcreateAmountField.getText().length()>0 && billcreateNameField.getText().length()>0){
            Bill bill = new Bill();
            bill.name = billcreateNameField.getText();
            bill.amount = Integer.parseInt(billcreateAmountField.getText());
            Random rand = new Random();
            int ID = abs(rand.nextInt());
            for (Bill bill1 : db.bills) {
                if (ID == bill1.ID) {
                    ID = abs(rand.nextInt());
                }
            }
            bill.ID = ID;
            for (UserBank userBank:db.user_banks){
                if(userBank.user.username.equals(billcreateUserField.getText()) && userBank.bank.name.equals(sm.read_object().bank.name)){
                    bill.userBank = userBank;
                }
            }
            bill.store();
            Logger logger = new Logger();
            logger.logger("New bill created: name: " + bill.name + " user: " +bill.userBank.user.username+" ID: " + bill.ID + " Amount: " + Integer.toString(bill.amount));
        }else{
            billcreateMessageLabel.setTextFill(Color.web("#F5130C"));
            billcreateMessageLabel.setText("You should enter Information needed!");
        }
    }
}
