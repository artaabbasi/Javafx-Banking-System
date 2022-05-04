package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ChooseBankPageController {
    @FXML
    private MenuButton choosebankMenuButton;
    @FXML
    private Button choosebankCreateButton;
    @FXML
    private Button choosebankRefreshButton;

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
                        try {
                            scene.onChangeScene("customer_bank_page.fxml");
                        } catch (IOException ex) {
                            ex.printStackTrace();
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
