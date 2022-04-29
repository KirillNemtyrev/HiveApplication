package com.example.crypto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WindowOpen {

    private static Scene scene;

    public static void open(String name, String file, double weight, double height){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(file));
            scene = new Scene(fxmlLoader.load(), weight, height);
            stage.setResizable(false);
            stage.setTitle(name);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openWebpage(String urlString) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(urlString));
            } catch (IOException | URISyntaxException e){
                throw new RuntimeException(e);
            }
        }
    }
}
