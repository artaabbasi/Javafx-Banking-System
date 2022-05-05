package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import com.bank.DB.DB;
import com.bank.DB.FileManager;
import com.bank.DB.Session;
import com.bank.DB.SessionManager;
import com.bank.DB.UserBank;
import com.bank.DB.User;
import com.bank.DB.Service;
import com.bank.DB.Bank;
import java.io.IOException;

public class LoginPageController {


    @FXML
    private Button loginButton;
    @FXML
    private Button loginSignupButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;

    public void onLoginButton(ActionEvent clickEvent) throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        Boolean flag = false;
        for (User user : db.users) {
            if (username.equals(user.username)){
                if (password.equals(user.password)) {
                    loginMessageLabel.setTextFill(Color.web("#5DF50C"));
                    loginMessageLabel.setText("You logged in successfully!");
                    SessionManager sm = new SessionManager();
                    Session session = sm.read_object();
                    session.user = user;
                    session.save();
                    flag = true;
                    BankApplication scene = new BankApplication();
                    scene.onChangeScene("choose_bank_page.fxml");
                }else {
                    loginMessageLabel.setTextFill(Color.web("#F5130C"));
                    loginMessageLabel.setText("Entered password is wrong!");
                }
            }
        }
        if (!flag){
            loginMessageLabel.setTextFill(Color.web("#F5130C"));
            loginMessageLabel.setText("Entered credentials dose not match our data!");
        }
    }
    public void onSignupButton(ActionEvent clickEvent) throws IOException {
        BankApplication scene = new BankApplication();
        scene.onChangeScene("signup_page.fxml");
    }

}
