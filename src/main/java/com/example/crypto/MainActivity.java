package com.example.crypto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane MainBanner;

    @FXML
    private Label fieldAuthor;

    @FXML
    private Label fieldBalance;

    @FXML
    private Label fieldCreateFerm;

    @FXML
    private Label fieldFarms;

    @FXML
    private ImageView fieldGitHub;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldName;

    @FXML
    private AnchorPane fieldNoFerm;

    @FXML
    private ImageView fieldUpdate;

    @FXML
    void initialize() {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Get full info account and write fields
        JSONObject account = Request.GetAccount();
        JSONArray farms = (JSONArray) account.get("farms");
        JSONObject profile = (JSONObject) account.get("profile");
        int fermCount = farms.size();
        if(fermCount != 0){
            fieldNoFerm.setVisible(false);
        }
        String balance = "$ " + account.get("balance");
        fieldBalance.setText(balance);
        fieldName.setText((String) profile.get("name"));
        fieldFarms.setText(String.valueOf(fermCount));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Action mouse on Image Farm
        fieldImage.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://hiveon.com/"));
        // Action mouse on Image GitHub
        fieldGitHub.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://github.com/KirillNemtyrev"));
        // Action mouse on update
        fieldUpdate.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) fieldCreateFerm.getScene().getWindow();
            WindowPage.updateWindow(stage, "Главная", "main_activity.fxml", 950, 665);
        });
        // Action mouse on Author Field
        fieldAuthor.setOnMouseEntered(event -> fieldAuthor.setStyle("-fx-text-fill:black"));
        fieldAuthor.setOnMouseExited(event -> fieldAuthor.setStyle("-fx-text-fill:gray"));
        fieldAuthor.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://vk.com/kirill_9085"));
        // Action mouse on CreateFerm
        fieldCreateFerm.setOnMouseEntered(event -> fieldCreateFerm.setStyle("-fx-text-fill:black"));
        fieldCreateFerm.setOnMouseExited(event -> fieldCreateFerm.setStyle("-fx-text-fill:#c3c3c3"));
        fieldCreateFerm.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) fieldCreateFerm.getScene().getWindow();
            WindowPage.openModal(stage, "Новая ферма", "new_ferm.fxml", 509, 400);
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

}