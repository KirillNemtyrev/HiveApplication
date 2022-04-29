package com.example.crypto;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConfigFile.LoadCFG();
        if(Request.ACCESS_TOKEN.isEmpty() || Request.CheckAuthentication() != Request.CODE_AUTHENTICATED_TOKEN) {
            WindowOpen.open("Авторизация", "auth.fxml", 600, 400);
            return;
        }

        WindowOpen.open("Главная", "main_activity.fxml", 950, 665);
    }

    public static void main(String[] args) {
        launch();
    }
}
