package com.bank.bank;

import com.bank.DB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ServicePageController {

    @FXML
    private Label serviceLabel1;
    @FXML
    private Label serviceLabel2;
    @FXML
    private Label serviceLabel3;
    @FXML
    private Label servicePriceLabel;
    @FXML
    private Label serviceDescriptLabel;
    @FXML
    private TextField serviceField1;
    @FXML
    private TextField serviceField3;
    @FXML
    private TextField serviceField2;
    @FXML
    private TextField servicePriceField;
    @FXML
    private Button serviceSubmitButton;
    @FXML
    private Button serviceStartButton;
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
    public void onStartButton(ActionEvent clickEvent) throws IOException {
        serviceStartButton.setVisible(false);
        serviceSubmitButton.setVisible(true);
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
//        FileManager fm = new FileManager();
//        DB db = fm.read_object();
        Service service = session.service;
        if (service.first_lable.length() > 1){
            serviceField1.setVisible(true);
            serviceLabel1.setVisible(true);
            serviceLabel1.setText(service.first_lable);
        }

        if (service.secend_lable.length() > 1){
            serviceField2.setVisible(true);
            serviceLabel2.setVisible(true);
            serviceLabel2.setText(service.secend_lable);
        }
        if (service.thired_label.length() > 1){
            serviceField3.setVisible(true);
            serviceLabel3.setVisible(true);
            serviceLabel3.setText(service.thired_label);
        }
        if (service.description.length() > 1){
            serviceDescriptLabel.setVisible(true);
            serviceDescriptLabel.setText(service.description);
        }
        if (service.has_price){
            servicePriceLabel.setVisible(true);
            servicePriceField.setVisible(true);
        }
    }
    public void onSubmitButton(ActionEvent clickEvent) throws IOException {
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        String message = "";
        Logger logger = new Logger();

        Service service = session.service;
        if (serviceField1.isVisible()){
            String field1 = serviceField1.getText();
            message += " " + service.first_lable + ": " + field1;
        }
        if (serviceField2.isVisible()){
            String field2 = serviceField2.getText();
            message += " " + service.secend_lable + ": " + field2;

        }
        if (serviceField3.isVisible()){
            String field3 = serviceField3.getText();
            message += " " + service.thired_label + ": " + field3;
        }
        if (servicePriceLabel.isVisible()){
            try {
                int price = Integer.parseInt(servicePriceField.getText());
                session.userBank.balance -= price;
                logger.logger("User balance decreased: user: "+ session.user.username + " Amount: " + Integer.toString(price));
            }catch(Exception e) {
            }
        }
        logger.logger("Service submitted: user: " + session.user.username + message);

        this.back();


    }
}
