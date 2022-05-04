package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

import java.io.IOException;

public class CustomerBankPageController {
    @FXML
    private Label cubankWelcomeLabel;
    @FXML
    private Label cubankUsernameField;
    @FXML
    private Label cubankTransferField;
    @FXML
    private Label cubankShabaField;
    @FXML
    private Label cubankUsernameLabel;
    @FXML
    private Label cubankTransferLabel;
    @FXML
    private Label cubankShabaLabel;
    @FXML
    private MenuButton cubankServiceMenuButton;
    @FXML
    private Button cubankStartButton;
    @FXML
    private Button cubankAdminButton;

    public void onStartButton(ActionEvent clickEvent) throws IOException {
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        Bank bank = session.bank;
        cubankStartButton.setVisible(false);
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        boolean is_admin = false;
        for (UserBank userBank: db.user_banks){
            if (userBank.user.IDcard.equals(session.user.IDcard)){
                if (userBank.bank.name.equals(session.bank.name)){
                    is_admin = userBank.is_admin;
                    cubankUsernameField.setText(userBank.user.username);
                    cubankTransferField.setText(userBank.transfer_code);
                    cubankShabaField.setText(userBank.shaba_code);
                    break;
                }
            }
        }
        cubankWelcomeLabel.setText("Welcome to "+bank.name+" Bank.");
        cubankServiceMenuButton.setVisible(true);
        cubankAdminButton.setVisible(is_admin);
        cubankUsernameField.setVisible(true);
        cubankTransferField.setVisible(true);
        cubankShabaField.setVisible(true);
        cubankUsernameLabel.setVisible(true);
        cubankTransferLabel.setVisible(true);
        cubankShabaLabel.setVisible(true);
    }
    public void onAdminButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("admin_page.fxml");
    }
}
