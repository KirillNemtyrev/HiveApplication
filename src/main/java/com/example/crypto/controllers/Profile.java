package com.example.crypto.controllers;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.Account;
import com.example.crypto.methods.Farm;
import com.example.crypto.methods.Request;
import com.example.crypto.methods.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Date;

public class Profile {

    @FXML
    private Button btn2FA;

    @FXML
    private Label btnAccount;

    @FXML
    private Button btnChangeEmail;

    @FXML
    private Label btnChangeUser;

    @FXML
    private Button btnDelete;

    @FXML
    private Label btnFarm;

    @FXML
    private Label btnPayment;

    @FXML
    private Label btnReferals;

    @FXML
    private Label btnSignOut;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label fieldAddFerm;

    @FXML
    private Label fieldBalance;

    @FXML
    private TextField fieldChangeEmail;

    @FXML
    private TextField fieldChangeName;

    @FXML
    private TextArea fieldCompany;

    @FXML
    private TextField fieldConfirmPass;

    @FXML
    private Label fieldEmailConfirm;

    @FXML
    private Label fieldFarms;

    @FXML
    private ImageView fieldGitHub;

    @FXML
    private TextField fieldIP;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldName;

    @FXML
    private TextField fieldNewPass;

    @FXML
    private TextField fieldOldPass;

    @FXML
    private TextField fieldPhone;

    @FXML
    private TextField fieldSkype;

    @FXML
    private TextField fieldTelegram;

    @FXML
    private TextField fieldTrack;

    @FXML
    private Label labelSuccessful;

    @FXML
    void initialize() {
        eventChangeText();

        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();
    }

    @FXML
    public void eventMouseOnEntered(){
        btnFarm.setOnMouseEntered(mouseEvent ->
                btnFarm.setTextFill(Paint.valueOf("#656060")));
        btnAccount.setOnMouseEntered(mouseEvent ->
                btnAccount.setTextFill(Paint.valueOf("#656060")));
        btnReferals.setOnMouseEntered(mouseEvent ->
                btnReferals.setTextFill(Paint.valueOf("#656060")));
        btnPayment.setOnMouseEntered(mouseEvent ->
                btnPayment.setTextFill(Paint.valueOf("#656060")));
        btnChangeUser.setOnMouseEntered(mouseEvent ->
                btnChangeUser.setTextFill(Paint.valueOf("#656060")));
        btnSignOut.setOnMouseEntered(mouseEvent ->
                btnSignOut.setTextFill(Paint.valueOf("#542323")));

        btnUpdate.setOnMouseEntered(event ->
                btnUpdate.setStyle("-fx-background-color: #8d949a"));
        btnChangeEmail.setOnMouseEntered(event ->
                btnChangeEmail.setStyle("-fx-background-color: #8d949a"));
        btn2FA.setOnMouseEntered(event ->
                btn2FA.setStyle("-fx-background-color: #8d949a"));
        btnDelete.setOnMouseEntered(event ->
                btnDelete.setStyle("-fx-background-color: #6b2f2f"));
    }

    @FXML
    public void eventMouseOnExited(){
        btnFarm.setOnMouseExited(mouseEvent ->
                btnFarm.setTextFill(Paint.valueOf("#9e9e9e")));
        btnAccount.setOnMouseExited(mouseEvent ->
                btnAccount.setTextFill(Paint.valueOf("#9e9e9e")));
        btnReferals.setOnMouseExited(mouseEvent ->
                btnReferals.setTextFill(Paint.valueOf("#9e9e9e")));
        btnPayment.setOnMouseExited(mouseEvent ->
                btnPayment.setTextFill(Paint.valueOf("#9e9e9e")));
        btnChangeUser.setOnMouseExited(mouseEvent ->
                btnChangeUser.setTextFill(Paint.valueOf("#9e9e9e")));
        btnChangeUser.setOnMouseExited(mouseEvent ->
                btnChangeUser.setTextFill(Paint.valueOf("#9e9e9e")));
        btnSignOut.setOnMouseExited(mouseEvent ->
                btnSignOut.setTextFill(Paint.valueOf("#943e3e")));

        btnUpdate.setOnMouseExited(event ->
                btnUpdate.setStyle("-fx-background-color: #c6ccd2"));
        btnChangeEmail.setOnMouseExited(event ->
                btnChangeEmail.setStyle("-fx-background-color: #c6ccd2"));
        btn2FA.setOnMouseExited(event ->
                btn2FA.setStyle("-fx-background-color: #c6ccd2"));
        btnDelete.setOnMouseExited(event ->
                btnDelete.setStyle("-fx-background-color: #943e3e"));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));
        fieldGitHub.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://github.com/KirillNemtyrev/crypto"));

        btnFarm.setOnMouseClicked(mouseEvent ->{
            Stage stage = (Stage) btnChangeUser.getScene().getWindow();
            WindowPage.updateWindow(stage, "Фермы", "farms.fxml", 950, 665, false);
        });
        btnChangeUser.setOnMouseClicked(mouseEvent -> {
            Request.Logout();
            Settings.setSettingToken(null);
            Settings.saveParams();

            Stage stage = (Stage) btnChangeUser.getScene().getWindow();
            WindowPage.updateWindow(stage, "Авторизация", "auth.fxml", 600, 400);
        });
        btnSignOut.setOnMouseClicked(mouseEvent -> {
            Request.Logout();
            Settings.setSettingToken(null);
            Settings.saveParams();

            System.exit(0);
        });

        btnUpdate.setOnAction(actionEvent -> {
            labelSuccessful.setVisible(true);
        });
    }

    @FXML
    public void eventChangeText(){
        String balance = Account.getBalance() + " $";
        fieldBalance.setText(balance);

        int hour = new Date().getHours();
        String welcome = (hour < 6 ? "Доброй ночи" : hour < 12 ? "Доброго утра" :
                hour < 18 ? "Доброго дня" : "Доброго вечера") + " , " + Account.getLogin();

        fieldName.setText(welcome);
        fieldFarms.setText(String.valueOf(Farm.getCount_farm()));

        fieldIP.setText(Account.getIp_address());
        fieldTrack.setText(Account.getTracking_id());

        fieldChangeName.setText(Account.getName());
        fieldChangeEmail.setText(Account.getEmail());

        String email_confirm = "ваш e-mail адрес " + (Account.isEmail_confirm() ? "подтверждён" : "неподстверждён");
        Paint color_email = Paint.valueOf( Account.isEmail_confirm() ? "#28d172" : "#d32727" );
        fieldEmailConfirm.setText(email_confirm);
        fieldEmailConfirm.setTextFill(color_email);

        String text_2fa = Account.isCode_enabled() ? "Выключить" : "Включить";
        btn2FA.setText(text_2fa);

        fieldPhone.setText(Account.getPhone());
        fieldSkype.setText(Account.getSkype());
        fieldTelegram.setText(Account.getTelegram());
        fieldCompany.setText(Account.getCompany());
    }
}
