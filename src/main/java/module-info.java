module com.bank.bank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bank.bank to javafx.fxml;
    exports com.bank.bank;
}