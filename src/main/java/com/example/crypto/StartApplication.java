package com.example.crypto;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConfigFile.LoadCFG();
        if(Request.ACCESS_TOKEN == null || Request.CheckAuthentication() != Request.CODE_AUTHENTICATED_TOKEN) {
            WindowPage.open("Авторизация", "auth.fxml", 600, 400);
            return;
        }

        WindowPage.open("Главная", "main_activity.fxml", 950, 665);
    }

    public static void main(String[] args) {
        launch();
    }
}
