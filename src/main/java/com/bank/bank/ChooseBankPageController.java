package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.WindowEvent;

import java.io.IOException;
import com.bank.DB.DB;
import com.bank.DB.FileManager;
import com.bank.DB.Session;
import com.bank.DB.SessionManager;
import com.bank.DB.UserBank;
import com.bank.DB.User;
import com.bank.DB.Service;
import com.bank.DB.Bank;
public class ChooseBankPageController {
    @FXML
    private MenuButton choosebankMenuButton;
    @FXML
    private Button choosebankCreateButton;
    @FXML
    private Button choosebankRefreshButton;
    @FXML
    private Button backButton;
    public void back() throws IOException {
        BankApplication scene = new BankApplication();
        try {
            scene.onChangeScene("login_page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void onChoosebankCreateButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("create_bank_page.fxml");
    }
    public void onChoosebankRefreshButton(ActionEvent clickEvent) throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        boolean flag = true;
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String bankname = ((MenuItem)e.getSource()).getText();
                for (Bank bank : db.banks ){
                    if (bank.name.equals(bankname)){
                        session.bank = bank;
                        session.save();
                        BankApplication scene = new BankApplication();
                        boolean flagg = true;
                        for (UserBank userBank : db.user_banks) {
                            if (userBank.user.username.equals(session.user.username) && userBank.bank.name.equals(session.bank.name)) {
                                flagg = false;
                                session.userBank = userBank;
                                session.save();
                                if (userBank.is_approved) {
                                    try {
                                        scene.onChangeScene("customer_bank_page.fxml");
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    try {
                                        scene.onChangeScene("user_bank_create_page.fxml");
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                        if (flagg) {
                            UserBank userBank = new UserBank(session.user, session.bank);
                            userBank.save();
                            session.userBank = userBank;
                            session.save();
                            try {
                                scene.onChangeScene("user_bank_create_page.fxml");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        for (Bank bank:db.banks){
            MenuItem mi = new MenuItem(bank.name);
            mi.setOnAction(event1);
            for (MenuItem m:choosebankMenuButton.getItems()){
                if (mi.getText().equals(m.getText())){
                    flag = false;
                }
            }
            if (flag){
                choosebankMenuButton.getItems().add(mi);
            }
        }
    }
}
