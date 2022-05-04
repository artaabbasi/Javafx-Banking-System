package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateServicePageController {
    @FXML
    private TextField serviceNameField;
    @FXML
    private TextField serviceFirstField;
    @FXML
    private TextField serviceSecondField;
    @FXML
    private TextField serviceThirdField;
    @FXML
    private TextField serviceDescriptionField;
    @FXML
    private Button serviceSubmitButton;

    public void onSubmitButton(ActionEvent clickEvent) throws IOException {
        Service service = new Service(serviceNameField.getText(), serviceFirstField.getText(), serviceSecondField.getText(), serviceThirdField.getText(), serviceDescriptionField.getText());
        SessionManager sm = new SessionManager();
        Session session = sm.read_object();
        service.bank = session.bank;
        service.save();
        System.out.println(service);
        BankApplication scene = new BankApplication();
        scene.onChangeScene("admin_page.fxml");
    }
}
