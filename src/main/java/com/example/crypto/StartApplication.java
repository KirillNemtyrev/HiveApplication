package com.example.crypto;

import com.example.crypto.methods.Account;
import com.example.crypto.methods.Request;
import com.example.crypto.methods.Settings;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) {
        Settings.getParams();
        String AccessToken = Settings.getSettingToken();

        if (Request.checkAccessToken(AccessToken) == Request.CODE_AUTHENTICATED_TOKEN) {

            Account.setAccessToken(AccessToken);
            if(Request.getAccount() == null || Request.getFarms() == null){
                WindowPage.open("Авторизация", "auth.fxml", 600, 400);
                return;
            }
            WindowPage.open("Главная", "farms.fxml", 950, 665);
            return;
        }
        WindowPage.open("Авторизация", "auth.fxml", 600, 400);
    }

    public static void main(String[] args) {
        launch();
    }
}
