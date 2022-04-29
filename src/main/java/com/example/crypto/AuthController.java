package com.example.crypto;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAuth;

    @FXML
    private AnchorPane clickedImage;

    @FXML
    private Label fieldAuthor;

    @FXML
    private ImageView fieldImage;

    @FXML
    private ImageView fieldGitHub;

    @FXML
    private Label fieldRecovery;

    @FXML
    private CheckBox fieldSave;

    @FXML
    private TextField fieldToken;

    @FXML
    private ImageView fieldShowPassword;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField field2FA;

    @FXML
    private AnchorPane clickedCopy;

    @FXML
    void initialize() {
        // Action mouse on Image Farm
        fieldImage.setOnMouseEntered(event -> {
            clickedImage.setVisible(true);
            clickedImage.setStyle("-fx-background-color: #22262b");
        });
        fieldImage.setOnMouseExited(event -> {
            clickedImage.setVisible(false);
            clickedImage.setStyle("-fx-background-color: #22262b");
        });
        fieldImage.setOnMouseClicked(mouseEvent -> {
            WindowPage.openWebpage("https://hiveon.com/");
            clickedImage.setStyle("-fx-background-color: lime");
        });
        // Action mouse on Image ShowPassword
        fieldShowPassword.setOnMouseEntered(event -> {
            clickedCopy.setVisible(true);
            clickedCopy.setStyle("-fx-background-color: #22262b");
        });
        fieldShowPassword.setOnMouseExited(event -> {
            clickedCopy.setVisible(false);
            clickedCopy.setStyle("-fx-background-color: #22262b");
        });
        fieldShowPassword.setOnMouseClicked(mouseEvent -> {

            if(fieldPassword.getText().trim().isEmpty()) return;

            clickedCopy.setStyle("-fx-background-color:lime");
            StringSelection stringSelection = new StringSelection(fieldPassword.getText().trim());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });
        // Action mouse on Image GitHub
        fieldGitHub.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://github.com/KirillNemtyrev/crypto"));
        // Action mouse on Author Field
        fieldAuthor.setOnMouseEntered(event -> fieldAuthor.setStyle("-fx-text-fill:black"));
        fieldAuthor.setOnMouseExited(event -> fieldAuthor.setStyle("-fx-text-fill:gray"));
        fieldAuthor.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://vk.com/kirill_9085"));
        // Action mouse on Recovery Field
        fieldRecovery.setOnMouseEntered(event -> fieldRecovery.setStyle("-fx-text-fill:black"));
        fieldRecovery.setOnMouseExited(event -> fieldRecovery.setStyle("-fx-text-fill:gray"));
        fieldRecovery.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://github.com/KirillNemtyrev"));
        // Input text field
        fieldToken.textProperty().addListener((observableValue, oldValue, newValue) -> {
            fieldToken.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black");
        });
        fieldPassword.textProperty().addListener((observableValue, oldValue, newValue) -> {
            fieldPassword.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: black");
        });
        // Action mouse on Auth Button
        btnAuth.setOnMouseEntered(event -> btnAuth.setStyle("-fx-border-color: black; -fx-background-color: #c6ccd2"));
        btnAuth.setOnMouseExited(event -> btnAuth.setStyle("-fx-border-color: black; -fx-background-color: LavenderBlush"));
        btnAuth.setOnAction(event -> {
            // Get Text in field
            String login = fieldToken.getText().trim();
            String password = fieldPassword.getText().trim();
            String twofa_code = field2FA.getText().trim();
            // Not succesfull
            if(login.isEmpty() || password.isEmpty() ||
                    Request.Authentication(login, password, twofa_code) != Request.CODE_AUTHENTICATION_TOKEN) {
                fieldToken.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                fieldPassword.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                field2FA.setStyle("-fx-background-color: #c6ccd2; -fx-border-color: red");
                return;
            }

            // Succesfull
            boolean save_input = fieldSave.isSelected();
            if(save_input) {
                ConfigFile.setLOGIN(login);
                ConfigFile.setPASSWORD(password);
            }
            ConfigFile.setSave(save_input);
            ConfigFile.SaveCFG();
            //Request.GetAccount();

            Stage stage = (Stage) btnAuth.getScene().getWindow();
            WindowPage.updateWindow(stage, "Главная", "main_activity.fxml", 950, 665);
        });
    }

}