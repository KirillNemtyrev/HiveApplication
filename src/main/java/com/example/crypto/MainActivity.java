package com.example.crypto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSupport;

    @FXML
    private static AnchorPane clickedImage;

    @FXML
    private Label fieldAuthor;

    @FXML
    private ImageView fieldGitHub;

    @FXML
    private ImageView fieldImage;

    @FXML
    void initialize() {
        // Action mouse on Image Farm
        fieldImage.setOnMouseEntered(event -> {
            clickedImage.setVisible(true);
            clickedImage.setStyle("-fx-background-color: white");
        });
        fieldImage.setOnMouseExited(event -> {
            clickedImage.setVisible(false);
            clickedImage.setStyle("-fx-background-color: white");
        });
        fieldImage.setOnMouseClicked(mouseEvent -> {
            WindowOpen.openWebpage("https://hiveon.com/");
            clickedImage.setStyle("-fx-background-color: lime");
        });
        // Action mouse on Image GitHub
        fieldGitHub.setOnMouseClicked(mouseEvent -> WindowOpen.openWebpage("https://github.com/KirillNemtyrev"));
        // Action mouse on Author Field
        fieldAuthor.setOnMouseEntered(event -> fieldAuthor.setStyle("-fx-text-fill:black"));
        fieldAuthor.setOnMouseExited(event -> fieldAuthor.setStyle("-fx-text-fill:gray"));
        fieldAuthor.setOnMouseClicked(mouseEvent -> WindowOpen.openWebpage("https://vk.com/kirill_9085"));
        // Action mouse on btnSupport
        btnSupport.setOnMouseEntered(event -> btnSupport.setStyle("-fx-background-color: #c6ccd2; -fx-background-radius: 15px"));
        btnSupport.setOnMouseExited(event -> btnSupport.setStyle("-fx-background-color: LavenderBlush; -fx-background-radius: 15px"));

    }

}