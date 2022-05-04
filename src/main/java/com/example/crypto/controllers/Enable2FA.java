package com.example.crypto.controllers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.Account;
import com.example.crypto.methods.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Enable2FA {

    @FXML
    private WebView QrCode;

    @FXML
    private Button btnEnable;

    @FXML
    private TextField field2FA;

    @FXML
    private TextField fieldCode;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldSendCode;

    @FXML
    private Label textSecret;

    @FXML
    private Label textSuccess;

    @FXML
    void initialize() {
        eventInitWebImage();

        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();
        eventKeyOnField();
    }

    @FXML
    public void eventMouseOnEntered(){
        btnEnable.setOnMouseEntered(event ->
                btnEnable.setStyle("-fx-border-color: black; -fx-background-color: #c6ccd2"));
        textSecret.setOnMouseEntered(mouseEvent ->
                textSecret.setStyle("-fx-text-fill:black"));
        fieldSendCode.setOnMouseEntered(mouseEvent ->
                fieldSendCode.setStyle("-fx-text-fill:black"));
    }

    @FXML
    public void eventMouseOnExited(){
        btnEnable.setOnMouseExited(event ->
                btnEnable.setStyle("-fx-border-color: black; -fx-background-color: LavenderBlush"));
        textSecret.setOnMouseExited(mouseEvent ->
                textSecret.setStyle("-fx-text-fill:grey"));
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
        textSecret.setOnMouseClicked(mouseEvent -> {
            StringSelection stringSelection = new StringSelection(textSecret.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            textSecret.setStyle("-fx-text-fill:green");
        });
        btnEnable.setOnAction(event -> {
            String code = field2FA.getText().trim();
            String code_email = fieldCode.getText().trim();

            if(code.length() != 6 || Request.enable2FA(code, code_email) != Request.CODE_AUTHENTICATED_TOKEN){
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                fieldCode.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                return;
            }

            Account.setCode_enabled(true);
            Stage stage = (Stage) btnEnable.getScene().getWindow();
            WindowPage.updateWindow((Stage) stage.getOwner(), "Аккаунт", "profile.fxml", 950, 665, false);
            stage.close();
        });
        QrCode.setOnMouseClicked(event -> {
            WindowPage.openWebpage(Account.getQr_code());
            return;
        });
    }

    @FXML
    public void eventKeyOnField(){
        field2FA.textProperty().addListener((observableValue, oldValue, newValue) ->
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black"));
        fieldCode.textProperty().addListener((observableValue, oldValue, newValue) ->
                fieldCode.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black"));
    }

    @FXML
    public void eventInitWebImage(){
        textSecret.setText(Account.getSecret());

        WebEngine engine = QrCode.getEngine();
        engine.load(Account.getQr_code());
    }

}