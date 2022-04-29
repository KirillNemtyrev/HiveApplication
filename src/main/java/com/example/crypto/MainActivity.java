package com.example.crypto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONObject;

public class MainActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane clickedImage11;

    @FXML
    private Label fieldAuthor;

    @FXML
    private Label fieldBalance;

    @FXML
    private Label fieldFarms;

    @FXML
    private ImageView fieldGitHub;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldName;

    @FXML
    void initialize() {
        JSONObject account = Request.GetAccount();
        JSONObject profile = (JSONObject) account.get("profile");
        // Setup
        String balance = "$ " + account.get("balance");
        fieldBalance.setText(balance);
        fieldName.setText((String) profile.get("name"));

        // Action mouse on Image Farm
        fieldImage.setOnMouseClicked(mouseEvent -> WindowOpen.openWebpage("https://hiveon.com/"));
        // Action mouse on Image GitHub
        fieldGitHub.setOnMouseClicked(mouseEvent -> WindowOpen.openWebpage("https://github.com/KirillNemtyrev"));
        // Action mouse on Author Field
        fieldAuthor.setOnMouseEntered(event -> fieldAuthor.setStyle("-fx-text-fill:black"));
        fieldAuthor.setOnMouseExited(event -> fieldAuthor.setStyle("-fx-text-fill:gray"));
        fieldAuthor.setOnMouseClicked(mouseEvent -> WindowOpen.openWebpage("https://vk.com/kirill_9085"));

    }

}