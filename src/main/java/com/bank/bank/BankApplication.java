package com.bank.bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankApplication extends Application {
    public static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("welcome_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Welcome to banking project!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void onChangeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoad = new FXMLLoader(BankApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoad.load(), 600, 400);
        stg.setScene(scene);
    }

}