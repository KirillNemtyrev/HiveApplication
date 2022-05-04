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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeEmail {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Matcher matcher;

    @FXML
    private Button btnChange;

    @FXML
    private TextField field2FA;

    @FXML
    private TextField fieldCode;

    @FXML
    private TextField fieldEmail;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldSendEmail;

    @FXML
    private AnchorPane paneGood;

    @FXML
    private Label textAction;

    @FXML
    void initialize() {

        fieldEmail.setText(Account.getEmail());

        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();
        eventKeyOnField();
    }

    @FXML
    public void eventMouseOnEntered(){
        btnChange.setOnMouseEntered(event ->
                btnChange.setStyle("-fx-border-color: black; -fx-background-color: #c6ccd2"));
        fieldSendEmail.setOnMouseEntered(event ->
                fieldSendEmail.setStyle("-fx-text-fill:black"));
    }

    @FXML
    public void eventMouseOnExited(){
        fieldSendEmail.setOnMouseExited(mouseEvent ->
                fieldSendEmail.setStyle("-fx-text-fill:grey"));
        btnChange.setOnMouseExited(event ->
                btnChange.setStyle("-fx-border-color: black; -fx-background-color: LavenderBlush"));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));
        fieldSendEmail.setOnMouseClicked(mouseEvent -> {
            String email = fieldEmail.getText().trim();
            String code = field2FA.getText().trim();

            if(email.isEmpty() || !validate(email) || Request.updateEmail(email, code) != Request.CODE_AUTHENTICATED_TOKEN){
                fieldEmail.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                return;
            }

            paneGood.setStyle("-fx-background-color: #086123");
            textAction.setText("Код бы отправлен вам на почту, если его нет возможно подойдет и недавно отправленый");
        });

        btnChange.setOnAction(event -> {
            String email = fieldEmail.getText().trim();
            String code = fieldCode.getText().trim();

            //Request.sendEmailcode(Account.getLogin());
            if(email.isEmpty() || code.isEmpty() || Request.setupEmail(email, code) != Request.CODE_AUTHENTICATED_TOKEN){
                fieldEmail.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                paneGood.setStyle("-fx-background-color: red");
                return;
            }

            Request.getAccount();
            Stage stage = (Stage) btnChange.getScene().getWindow();
            WindowPage.updateWindow((Stage) stage.getOwner(), "Аккаунт", "profile.fxml", 950, 665, false);
            stage.close();
        });
    }

    @FXML
    public void eventKeyOnField(){
        fieldCode.textProperty().addListener((observableValue, oldValue, newValue) ->
                fieldCode.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black"));
        fieldEmail.textProperty().addListener((observableValue, oldValue, newValue) ->
                fieldEmail.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black"));
    }

    public boolean validate(final String hex) {
        matcher = Pattern.compile(EMAIL_PATTERN).matcher(hex);

        return matcher.matches();
    }

}
