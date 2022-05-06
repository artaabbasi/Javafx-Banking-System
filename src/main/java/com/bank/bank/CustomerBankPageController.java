package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import com.bank.DB.DB;
import com.bank.DB.FileManager;
import com.bank.DB.Session;
import com.bank.DB.SessionManager;
import com.bank.DB.UserBank;
import com.bank.DB.User;
import com.bank.DB.Service;
import com.bank.DB.Bank;
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
    private Label cubankBallanceField;
    @FXML
    private Label cubankBallanceLabel;
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
    @FXML
    private Button cubankTransferButton;
    @FXML
    private Button cubankBillButton;
    @FXML
    private Button cubankWithdrawButton;
    @FXML
    private Button cubankLoanButton;
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
    public void onStartButton(ActionEvent clickEvent) throws IOException {
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        Bank bank = session.bank;
        cubankStartButton.setVisible(false);
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        boolean is_admin = false;
        UserBank userBank = session.userBank;
        cubankUsernameField.setText(userBank.user.username);
        cubankTransferField.setText(userBank.transfer_code);
        cubankShabaField.setText(userBank.shaba_code);
        cubankBallanceField.setText(Integer.toString(userBank.balance));

        cubankWelcomeLabel.setText("Welcome to "+bank.name+" Bank.");
        cubankServiceMenuButton.setVisible(true);
        cubankAdminButton.setVisible(userBank.is_admin);
        cubankUsernameField.setVisible(true);
        cubankTransferField.setVisible(true);
        cubankShabaField.setVisible(true);
        cubankUsernameLabel.setVisible(true);
        cubankBallanceField.setVisible(true);
        cubankBallanceLabel.setVisible(true);
        cubankTransferLabel.setVisible(true);
        cubankShabaLabel.setVisible(true);
        cubankTransferButton.setVisible(true);
        cubankBillButton.setVisible(true);
        cubankWithdrawButton.setVisible(true);
        cubankLoanButton.setVisible(true);
        boolean flag = true;
        flag = true;
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String service = ((MenuItem)e.getSource()).getText();
                for (Service service1: db.services){
                    if (service1.bank.name.equals(session.bank.name)){
                        if (service1.name.equals(service)) {
                            session.service = service1;
                            BankApplication scene = new BankApplication();
                            session.save();
                            try {
                                scene.onChangeScene("service_page.fxml");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        for (Service service:db.services) {
            if (service.bank.name.equals(session.bank.name)) {
                MenuItem menuItem = new MenuItem(service.name);
                menuItem.setOnAction(event3);
                for (MenuItem m : cubankServiceMenuButton.getItems()) {
                    if (menuItem.getText().equals(m.getText())) {
                        flag = false;
                    }
                }
                if (flag) {
                    cubankServiceMenuButton.getItems().add(menuItem);
                }
            }
        }
    }
    public void onAdminButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("admin_page.fxml");
    }

    public void onTransferButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("transfer_page.fxml");
    }

    public void onBillButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("bill_page.fxml");
    }

    public void onWithdrawButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("withdraw.fxml");
    }
    public void onLoanButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("request_loan.fxml");
    }
}
