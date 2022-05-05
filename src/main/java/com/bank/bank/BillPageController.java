package com.bank.bank;

import com.bank.DB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class BillPageController {
    @FXML
    private TextField billIDField;
    @FXML
    private Label billMessageLabel;
    @FXML
    private Button billPayButton;
    @FXML
    private ListView billBillsList;
    @FXML
    private Button billRefreshButton;
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

    public void onRefreshButton() throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        ObservableList<String> bills = FXCollections.observableArrayList();
        boolean flag = true;
        for (Bill bill: db.bills){
            if (!bill.payed) {
                if (!(bill.userBank == null)) {
                    if (bill.userBank.user.username.equals(session.user.username)) {
                        for (String s : bills) {
                            if (s.equals(bill.name + ":" + Integer.toString(bill.ID) + ":" + Integer.toString(bill.amount))) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            bills.add(bill.name + ":" + Integer.toString(bill.ID) + ":" + Integer.toString(bill.amount));
                        }
                        flag = true;
                    }
                }
            }
        }
        billBillsList.setItems(bills);
    }
    public void onPayButton() throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        Bill localbill = new Bill();
        boolean flag = false;
        for (Bill bill: db.bills){
            if (!bill.payed) {
                if (bill.ID == Integer.parseInt(billIDField.getText())) {
                    if(bill.userBank.user.username.equals(session.user.username)){
                        localbill = bill;
                        flag = true;
                    }
                }
            }else {
                billMessageLabel.setTextFill(Color.web("#F5130C"));
                billMessageLabel.setText("you paid this bill once!");
            }
        }
        if (flag)
        {
            if (session.userBank.balance >= localbill.amount) {
                session.userBank.balance -= localbill.amount;
                localbill.payed = true;
                localbill.save();
                session.userBank.save();
                session.save();
                localbill.payed = true;
                localbill.save();
                billIDField.clear();
                billMessageLabel.setTextFill(Color.web("#5DF50C"));
                billMessageLabel.setText("You paid successfully!");
                Logger logger = new Logger();
                logger.logger("Bill payed: bill: " + localbill.ID);
                logger.logger("User balance decreased: user: " + session.user.username + " Amount: " + Integer.toString(localbill.amount));

            } else {
                billMessageLabel.setTextFill(Color.web("#F5130C"));
                billMessageLabel.setText("you dont have enough money in your account!");
            }
        }else {
            billMessageLabel.setTextFill(Color.web("#F5130C"));
            billMessageLabel.setText("Bill not found !");
        }
    }
}
