package com.bank.bank;

import com.bank.DB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class AdminPageController {
    public ObservableList<String> users = FXCollections.observableArrayList();
    public ObservableList<String> services = FXCollections.observableArrayList();
    @FXML
    private ListView adminUsersList;
    @FXML
    private  ListView adminServiceList;
    @FXML
    private MenuButton adminPendingUsersMenu;
    @FXML
    private Button adminRefreshButton;
    @FXML
    private Button adminCreateService;
    @FXML
    private Button adminBillButton;
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
    public void onRefreshButton(ActionEvent clickEvent) throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        for(MenuItem mm :adminPendingUsersMenu.getItems()){
            String muser = mm.getText();
            for (UserBank userBank: db.user_banks){
                if (userBank.user.username.equals(muser)) {
                    if (userBank.is_approved == true){
                        mm.setVisible(false);
                    }
                }
            }
        }
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        boolean flag = true;
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String username = ((MenuItem)e.getSource()).getText();
                for (UserBank userBank: db.user_banks){
                    if (userBank.user.username.equals(username)) {
                        userBank.is_approved = true;
                        userBank.save();
                        Logger logger = new Logger();
                        try {
                            logger.logger("User approved: user: " + userBank.user.username);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        };
        for (UserBank userBank: db.user_banks) {
            if (userBank.bank.name.equals(session.bank.name)) {
                if (userBank.is_approved) {
                    for (String s : users) {
                        if (s.equals(userBank.user.username)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        users.add(userBank.user.username);
                    }
                    flag = true;
                } else {
                    flag = true;
                    MenuItem mi = new MenuItem(userBank.user.username);
                    mi.setOnAction(event2);
                    for (MenuItem m : adminPendingUsersMenu.getItems()) {
                        if (mi.getText().equals(m.getText())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        adminPendingUsersMenu.getItems().add(mi);
                    }
                }
            } else {
            }
            adminUsersList.setItems(users);
            flag = true;
            for (Service service : db.services) {
                if (service.bank.name.equals(session.bank.name)) {
                    for (String s : services) {
                        if (s.equals(service.name)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        services.add(service.name);
                    }
                    flag = true;
                }
            }
        }
        adminServiceList.setItems(services);
    }
    public void onCreateService(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("create_service_page.fxml");

    }
    public void onBillButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("create_bill_page.fxml");
    }
}
