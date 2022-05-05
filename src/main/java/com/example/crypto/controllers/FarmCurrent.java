package com.example.crypto.controllers;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

public class FarmCurrent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pagination PageCount;

    @FXML
    private Label btnAccess;

    @FXML
    private Label btnAccount;

    @FXML
    private Label btnChangeUser;

    @FXML
    private Label btnEnergy;

    @FXML
    private Label btnExitForFarm;

    @FXML
    private Label btnMain;

    @FXML
    private Label btnPayment;

    @FXML
    private Label btnSignOut;

    @FXML
    private Label btnStatistic;

    @FXML
    private Label btnTransferFarm;

    @FXML
    private Label btnTransferMoney;

    @FXML
    private Label btnWorkers;

    @FXML
    private Label fieldBalance;

    @FXML
    private Label fieldGPU;

    @FXML
    private ImageView fieldGitHub;

    @FXML
    private ImageView fieldImage;

    @FXML
    private Label fieldName;

    @FXML
    private Label fieldNameFarm;

    @FXML
    private Label fieldPower;

    @FXML
    private Label fieldRIGS;

    @FXML
    private Label fieldRole;

    @FXML
    private Label fieldWorkers;

    @FXML
    void initialize() {
        eventChangeText();

        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();

        initWorkers();
    }

    @FXML
    public void eventMouseOnEntered(){
        btnMain.setOnMouseEntered(mouseEvent ->
                btnMain.setTextFill(Paint.valueOf("#656060")));
        btnAccount.setOnMouseEntered(mouseEvent ->
                btnAccount.setTextFill(Paint.valueOf("#656060")));
        btnPayment.setOnMouseEntered(mouseEvent ->
                btnPayment.setTextFill(Paint.valueOf("#656060")));
        btnChangeUser.setOnMouseEntered(mouseEvent ->
                btnChangeUser.setTextFill(Paint.valueOf("#656060")));
        btnSignOut.setOnMouseEntered(mouseEvent ->
                btnSignOut.setTextFill(Paint.valueOf("#542323")));

        btnWorkers.setOnMouseEntered(mouseEvent ->
                btnWorkers.setTextFill(Paint.valueOf("#656060")));
        btnStatistic.setOnMouseEntered(mouseEvent ->
                btnStatistic.setTextFill(Paint.valueOf("#656060")));
        btnEnergy.setOnMouseEntered(mouseEvent ->
                btnEnergy.setTextFill(Paint.valueOf("#656060")));
        btnAccess.setOnMouseEntered(mouseEvent ->
                btnAccess.setTextFill(Paint.valueOf("#656060")));
        btnTransferFarm.setOnMouseEntered(mouseEvent ->
                btnTransferFarm.setTextFill(Paint.valueOf("#656060")));
        btnTransferMoney.setOnMouseEntered(mouseEvent ->
                btnTransferMoney.setTextFill(Paint.valueOf("#656060")));
        btnExitForFarm.setOnMouseEntered(mouseEvent ->
                btnExitForFarm.setTextFill(Paint.valueOf("#542323")));
    }

    @FXML
    public void eventMouseOnExited(){
        btnMain.setOnMouseExited(mouseEvent ->
                btnMain.setTextFill(Paint.valueOf("#9e9e9e")));
        btnAccount.setOnMouseExited(mouseEvent ->
                btnAccount.setTextFill(Paint.valueOf("#9e9e9e")));
        btnPayment.setOnMouseExited(mouseEvent ->
                btnPayment.setTextFill(Paint.valueOf("#9e9e9e")));
        btnChangeUser.setOnMouseExited(mouseEvent ->
                btnChangeUser.setTextFill(Paint.valueOf("#9e9e9e")));
        btnChangeUser.setOnMouseExited(mouseEvent ->
                btnChangeUser.setTextFill(Paint.valueOf("#9e9e9e")));
        btnSignOut.setOnMouseExited(mouseEvent ->
                btnSignOut.setTextFill(Paint.valueOf("#943e3e")));

        btnWorkers.setOnMouseExited(mouseEvent ->
                btnWorkers.setTextFill(Paint.valueOf("#9e9e9e")));
        btnStatistic.setOnMouseExited(mouseEvent ->
                btnStatistic.setTextFill(Paint.valueOf("#9e9e9e")));
        btnEnergy.setOnMouseExited(mouseEvent ->
                btnEnergy.setTextFill(Paint.valueOf("#9e9e9e")));
        btnAccess.setOnMouseExited(mouseEvent ->
                btnAccess.setTextFill(Paint.valueOf("#9e9e9e")));
        btnTransferFarm.setOnMouseExited(mouseEvent ->
                btnTransferFarm.setTextFill(Paint.valueOf("#9e9e9e")));
        btnTransferMoney.setOnMouseExited(mouseEvent ->
                btnTransferMoney.setTextFill(Paint.valueOf("#9e9e9e")));
        btnExitForFarm.setOnMouseExited(mouseEvent ->
                btnExitForFarm.setTextFill(Paint.valueOf("#943e3e")));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));
        fieldGitHub.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://github.com/KirillNemtyrev/crypto"));
        btnMain.setOnMouseClicked(mouseEvent ->{
            Stage stage = (Stage) btnChangeUser.getScene().getWindow();
            WindowPage.updateWindow(stage, "Фермы", "farms.fxml", 950, 665, false);
        });

        btnAccount.setOnMouseClicked(mouseEvent ->{
            Stage stage = (Stage) btnAccount.getScene().getWindow();
            WindowPage.updateWindow(stage, "Аккаунт", "profile.fxml", 950, 665, false);
        });
        btnChangeUser.setOnMouseClicked(mouseEvent -> {
            Request.Logout();
            Settings.setSettingToken(null);
            Settings.saveParams();

            Stage stage = (Stage) btnChangeUser.getScene().getWindow();
            WindowPage.updateWindow(stage, "Авторизация", "auth.fxml", 678, 505);
        });
        btnSignOut.setOnMouseClicked(mouseEvent -> {
            Request.Logout();
            Settings.setSettingToken(null);
            Settings.saveParams();

            System.exit(0);
        });
    }

    @FXML
    public void eventChangeText(){
        fieldBalance.setText(Farm.getBalanceFarm() + " $");
        fieldWorkers.setText(String.valueOf(Workers.getCountWorkers()));
        fieldGPU.setText(Farm.getCountGPU());
        fieldRIGS.setText(Farm.getCountRIGS());
        fieldPower.setText(Farm.getPowerFarm());

        fieldNameFarm.setText(Farm.getCurrentFarmName());
        fieldRole.setText(Farm.getCurrentFarmRole().toUpperCase());

        // Get time
        ZoneId zoneId = ZoneId.systemDefault();
        LocalTime time = LocalTime.now(zoneId);
        int hour = time.getHour();
        String welcome = (hour < 6 ? "Доброй ночи" : hour < 12 ? "Доброго утра" :
                hour < 18 ? "Доброго дня" : "Доброго вечера") + " , " + Account.getLogin();

        fieldName.setText(welcome);
    }

    @FXML
    public void initWorkers(){
        PageCount.setPageCount((int) Math.ceil((double) Workers.getCountWorkers()/13.0));

        setupPagination();
    }

    @FXML
    public void setupPagination(){
        PageCount.setPageFactory((pageIndex) -> {
            int count = 1;
            AnchorPane PagePane = createActivePane();
            while (13*pageIndex + count <= Workers.getCountWorkers() && count <= 13){

                final double posX = 50; // default
                double posY = 70 + 30.0*(count - 1);
                int index = 13*pageIndex + (count - 1);

                PagePane.getChildren().add(insertWorker(posX, posY, index));
                count++;
            }
            return PagePane;
        });
    }

    @FXML
    public AnchorPane createActivePane(){
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(950.0);
        pane.setPrefHeight(485);
        pane.setLayoutX(0);
        pane.setLayoutY(138);
        return pane;
    }

    @FXML
    public AnchorPane createPane(double posX, double posY, int farmId) {

        AnchorPane ListPane = new AnchorPane();
        ListPane.setLayoutX(posX);
        ListPane.setLayoutY(posY);
        ListPane.setPrefWidth(850);
        ListPane.setPrefHeight(23);
        ListPane.setCursor(Cursor.cursor("HAND"));
        ListPane.setStyle("-fx-background-color: #2f353c");

        ListPane.setOnMouseEntered(event -> ListPane.setStyle("-fx-background-color: #192128"));
        ListPane.setOnMouseExited(event -> ListPane.setStyle("-fx-background-color: #2f353c"));
        /*ListPane.setOnMouseClicked(event ->{

        });*/

        return ListPane;
    }

    @FXML
    public void addLabel(AnchorPane ListPane, String Aligmnment, String name, String color, double posX, double posY,
                         double prefWidth, double prefHeight) {
        Label Name = new Label(name);
        Name.setFont(Font.font("Consolas Bold", 16.0));
        Name.setTextFill(Paint.valueOf(color));
        Name.setLayoutX(posX);
        Name.setLayoutY(posY);
        Name.setPrefWidth(prefWidth);
        Name.setPrefHeight(prefHeight);
        if(!Aligmnment.isEmpty()) Name.setAlignment(Pos.valueOf(Aligmnment));
        ListPane.getChildren().add(Name);
    }

    @FXML
    public AnchorPane insertWorker(double posX, double posY, int index){

        JSONObject worker = Workers.getCurrentWorker(index);

        AnchorPane workerPane = createPane(posX, posY, index);

        String NAME = String.valueOf(worker.get("name"));
        String COLOR_NAME = "#c3c3c3";
        addLabel(workerPane, "", NAME, COLOR_NAME, 9.0, 0.0, 300.0,22.0);

        return workerPane;
    }
}
