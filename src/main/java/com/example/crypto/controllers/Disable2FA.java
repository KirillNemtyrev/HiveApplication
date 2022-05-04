package com.example.crypto.controllers;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.Account;
import com.example.crypto.methods.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Disable2FA {

    @FXML
    private Button btnDisable;

    @FXML
    private TextField field2FA;

    @FXML
    private ImageView fieldImage;

    @FXML
    void initialize() {
        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();
        eventKeyOnField();
    }

    @FXML
    public void eventMouseOnEntered(){
        btnDisable.setOnMouseEntered(event ->
                btnDisable.setStyle("-fx-border-color: black; -fx-background-color: #c6ccd2"));
    }

    @FXML
    public void eventMouseOnExited(){
        btnDisable.setOnMouseExited(event ->
                btnDisable.setStyle("-fx-border-color: black; -fx-background-color: LavenderBlush"));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));
        btnDisable.setOnAction(event -> {
            String code = field2FA.getText().trim();

            if(code.length() != 6 || Request.delete2FA(code) != Request.CODE_AUTHENTICATED_TOKEN){
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                return;
            }

            Account.setCode_enabled(false);
            Stage stage = (Stage) btnDisable.getScene().getWindow();
            WindowPage.updateWindow((Stage) stage.getOwner(), "Аккаунт", "profile.fxml", 950, 665, false);
            stage.close();
        });
    }

    @FXML
    public void eventKeyOnField(){
        field2FA.textProperty().addListener((observableValue, oldValue, newValue) ->
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black"));
    }

}