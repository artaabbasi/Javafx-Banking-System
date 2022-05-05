package com.bank.bank;
import com.bank.DB.DB;
import com.bank.DB.FileManager;
import com.bank.DB.Session;
import com.bank.DB.SessionManager;
import com.bank.DB.UserBank;
import com.bank.DB.User;
import com.bank.DB.Service;
import com.bank.DB.Bank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;


public class WellcomeController {
    @FXML
    private Button EnterButton;

    @FXML
    protected void onEnterButtonClick()  throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        Session session = new Session();
        SessionManager sm = new SessionManager();
        sm.write_object(session);
        BankApplication scene = new BankApplication();
        scene.onChangeScene("login_page.fxml");
    }
}