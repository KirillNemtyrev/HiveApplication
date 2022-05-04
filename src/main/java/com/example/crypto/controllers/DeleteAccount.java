package com.example.crypto.controllers;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.Account;
import com.example.crypto.methods.Request;
import com.example.crypto.methods.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeleteAccount {

    @FXML
    private Button btnRemove;

    @FXML
    private TextField field2FA;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldSendCode;

    @FXML
    private Label textSuccess;

    @FXML
    void initialize() {
        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();
        eventKeyOnField();
    }

    @FXML
    public void eventMouseOnEntered(){
        btnRemove.setOnMouseEntered(event ->
                btnRemove.setStyle("-fx-border-color: black; -fx-background-color: #c6ccd2"));
        fieldSendCode.setOnMouseEntered(mouseEvent ->
                fieldSendCode.setStyle("-fx-text-fill:black"));
    }

    @FXML
    public void eventMouseOnExited(){
        btnRemove.setOnMouseExited(event ->
                btnRemove.setStyle("-fx-border-color: black; -fx-background-color: LavenderBlush"));
        fieldSendCode.setOnMouseExited(mouseEvent ->
                fieldSendCode.setStyle("-fx-text-fill:grey"));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));

        fieldSendCode.setOnMouseClicked(event -> {

            if(Request.sendCode(Account.getLogin()) != Request.CODE_AUTHENTICATION_TOKEN)
            {
                fieldSendCode.setStyle("-fx-text-fill:red");
                textSuccess.setText("Невозможно отправить..");
                textSuccess.setVisible(true);
                return;
            }

            textSuccess.setText("Код был успешно отправлен!");
            textSuccess.setVisible(true);
        });

        btnRemove.setOnAction(event -> {
            String code = field2FA.getText().trim();

            if(code.length() != 6 || Request.deleteAccount(code) != Request.CODE_AUTHENTICATED_TOKEN){
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                return;
            }

            Stage stage = (Stage) btnRemove.getScene().getWindow();
            Stage primaryStage = (Stage) stage.getOwner();
            stage.close();

            Settings.setSettingRemember(false);
            Settings.saveParams();

            WindowPage.updateWindow(primaryStage, "Авторизация", "auth.fxml", 678, 505);
        });
    }

    @FXML
    public void eventKeyOnField(){
        field2FA.textProperty().addListener((observableValue, oldValue, newValue) ->
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black"));
    }

}
