package com.bank.bank;

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