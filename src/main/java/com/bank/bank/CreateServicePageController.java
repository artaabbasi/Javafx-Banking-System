package com.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
    @FXML
    private CheckBox servicePriceCheck;
    @FXML
    private CheckBox serviceCashCheck;
    @FXML
    private Label serviceMessageLabel;
    @FXML
    private Button backButton;
    public void back() throws IOException {
        BankApplication scene = new BankApplication();
        try {
            scene.onChangeScene("admin_page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void onSubmitButton(ActionEvent clickEvent) throws IOException {
        if (!(serviceNameField.getText().length() < 1)) {
            FileManager fm = new FileManager();
            DB db = fm.read_object();
            boolean flag = true;
            for (Service service:db.services){
                if (service.name.equals(serviceNameField.getText())){
                    flag = false;
                    serviceMessageLabel.setTextFill(Color.web("#F5130C"));
                    serviceMessageLabel.setText("Entered name exist!");
                    break;
                }
            }
            if (flag) {
                Service service = new Service(serviceNameField.getText(), serviceFirstField.getText(), serviceSecondField.getText(), serviceThirdField.getText(), serviceDescriptionField.getText());
                SessionManager sm = new SessionManager();
                Session session = sm.read_object();
                service.bank = session.bank;
                service.has_price = servicePriceCheck.isSelected();
                service.is_cash = serviceCashCheck.isSelected();
                service.save();
                BankApplication scene = new BankApplication();
                scene.onChangeScene("admin_page.fxml");
            }
        }else {
            serviceMessageLabel.setTextFill(Color.web("#F5130C"));
            serviceMessageLabel.setText("Enter name!");
        }
    }
}
