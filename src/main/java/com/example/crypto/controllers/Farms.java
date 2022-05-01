package com.example.crypto.controllers;

import java.util.Date;
import com.example.crypto.WindowPage;
import com.example.crypto.methods.Account;
import com.example.crypto.methods.Farm;
import com.example.crypto.methods.Request;
import com.example.crypto.methods.Settings;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

public class Farms {

    @FXML
    private Pagination PageCount;

    @FXML
    private Label btnAccount;

    @FXML
    private Label btnChangeUser;

    @FXML
    private Label btnFarm;

    @FXML
    private Label btnPayment;

    @FXML
    private Label btnReferals;

    @FXML
    private Label btnSignOut;

    @FXML
    private Label fieldAddFerm;

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
    void initialize() {
        eventChangeText();

        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();

        if(Farm.getCount_farm() != 0) initFarms();
    }

    @FXML
    public void eventMouseOnEntered(){
        fieldCreateFerm.setOnMouseEntered(event ->
                fieldCreateFerm.setStyle("-fx-text-fill:black"));
        fieldAddFerm.setOnMouseEntered(event ->
                fieldAddFerm.setStyle("-fx-text-fill:#656060"));
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
    }

    @FXML
    public void eventMouseOnExited(){
        fieldCreateFerm.setOnMouseExited(event ->
                fieldCreateFerm.setStyle("-fx-text-fill:#c3c3c3"));
        fieldAddFerm.setOnMouseExited(event ->
                fieldAddFerm.setStyle("-fx-text-fill:gray"));
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

    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));
        fieldGitHub.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://github.com/KirillNemtyrev/crypto"));
        fieldCreateFerm.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) fieldCreateFerm.getScene().getWindow();
            WindowPage.openModal(stage, "Новая ферма", "new_ferm.fxml", 509, 400);
        });
        fieldAddFerm.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) fieldAddFerm.getScene().getWindow();
            WindowPage.openModal(stage, "Новая ферма", "new_ferm.fxml", 509, 400);
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
            WindowPage.updateWindow(stage, "Авторизация", "auth.fxml", 600, 400);
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
        String balance = Account.getBalance() + " $";
        fieldBalance.setText(balance);

        int hour = new Date().getHours();
        String welcome = (hour < 6 ? "Доброй ночи" : hour < 12 ? "Доброго утра" :
                hour < 18 ? "Доброго дня" : "Доброго вечера") + " , " + Account.getLogin();

        fieldName.setText(welcome);
        fieldFarms.setText(String.valueOf(Farm.getCount_farm()));
    }

    @FXML
    public void initFarms(){
        fieldNoFerm.setVisible(false);
        fieldAddFerm.setVisible(true);
        PageCount.setVisible(true);
        PageCount.setPageCount((int) Math.ceil((double) Farm.getCount_farm()/4.0));

        setupPagination();
    }

    @FXML
    public void setupPagination(){
        PageCount.setPageFactory((pageIndex) -> {
            int count = 1;
            AnchorPane PagePane = createActivePane();
            while (4*pageIndex + count <= Farm.getCount_farm() && count <= 4){

                final double posX = 26.0; // default
                double posY = 60.0 + 100.0*(count - 1);
                int index = 4*pageIndex + (count - 1);

                //addList(posX, posY, index);
                PagePane.getChildren().add(inserFarm(posX, posY, index));
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
    public AnchorPane createPane(double posX, double posY) {

        AnchorPane ListPane = new AnchorPane();
        ListPane.setLayoutX(posX);
        ListPane.setLayoutY(posY);
        ListPane.setPrefWidth(899.0);
        ListPane.setPrefHeight(83.0);
        ListPane.setCursor(Cursor.cursor("HAND"));
        ListPane.setStyle("-fx-background-color: #2f353c");

        ListPane.setOnMouseEntered(event -> ListPane.setStyle("-fx-background-color: #192128"));
        ListPane.setOnMouseExited(event -> ListPane.setStyle("-fx-background-color: #2f353c"));

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
    public AnchorPane inserFarm(double posX, double posY, int index){

        JSONObject farm = Farm.getFarm(index);

        assert farm != null;
        JSONObject money = (JSONObject) farm.get("money");
        JSONObject stats = (JSONObject) farm.get("stats");

        AnchorPane farmPane = createPane(posX, posY);

        String NAME = String.valueOf(farm.get("name"));
        addLabel(farmPane, "", NAME, "#c3c3c3", 9.0, 5.0, 508.0,19.0);

        String Workers = String.valueOf(stats.get("workers_online"));
        addLabel(farmPane, "", Workers, "#c3c3c3", 13.0, 32.0, 49.0,19.0);
        addLabel(farmPane, "", "В СЕТИ", "GREY", 13.0, 51.0, 53.0,18.0);

        String GPUS = stats.get("gpus_online") + "/" + stats.get("gpus_total");
        addLabel(farmPane, "", GPUS, "#c3c3c3", 83.0, 32.0, 49.0,19.0);
        addLabel(farmPane, "", "GPUS", "GREY", 83.0, 51.0, 36.0,18.0);

        String RIGS = stats.get("rigs_online") + "/" + stats.get("rigs_total");
        addLabel(farmPane, "", RIGS, "#c3c3c3", 153.0, 32.0, 49.0,19.0);
        addLabel(farmPane, "", "RIGS", "GREY", 153.0, 51.0, 36.0,18.0);

        String ASICS = stats.get("asics_online") + "/" + stats.get("asics_total");
        addLabel(farmPane, "", ASICS, "#c3c3c3", 223.0, 32.0, 49.0,19.0);
        addLabel(farmPane, "", "ASICS", "GREY", 223.0, 51.0, 44.0,18.0);

        String BOARDS = stats.get("boards_online") + "/" + stats.get("boards_total");
        addLabel(farmPane, "", BOARDS, "#c3c3c3", 293.0, 32.0, 49.0,19.0);
        addLabel(farmPane, "", "BOARDS", "GREY", 293.0, 51.0, 53.0,18.0);

        String DEVICES = stats.get("devices_online") + "/" + stats.get("devices_total");
        addLabel(farmPane, "", DEVICES, "#c3c3c3", 363.0, 32.0, 49.0,19.0);
        addLabel(farmPane, "", "DEVICES", "GREY", 363.0, 51.0, 62.0,18.0);

        String MONTHLY_COST = money.get("monthly_cost") + " $";
        addLabel(farmPane, "CENTER_RIGHT", MONTHLY_COST, "#c3c3c3", 578.0, 32.0, 98.0,22.0);
        addLabel(farmPane, "", "МЕСЯЧНЫЙ ПЛАТЁЖ", "GREY", 544.0, 51.0, 132.0,18.0);

        String DISCOUNT = money.get("discount") + " %";
        addLabel(farmPane, "CENTER_RIGHT", DISCOUNT, "#c3c3c3", 687.0, 32.0, 98.0,22.0);
        addLabel(farmPane, "", "СКИДКА", "GREY", 736.0, 51.0, 53.0,18.0);

        String BALANCE = money.get("balance") + " $";
        addLabel(farmPane, "CENTER_RIGHT", BALANCE, "#c3c3c3", 785.0, 32.0, 98.0,22.0);
        addLabel(farmPane, "", "БАЛАНС", "GREY", 834.0, 51.0, 53.0,18.0);

        String POWER = farm.get("hardware_power_draw") + " ВТ";
        addLabel(farmPane, "CENTER_RIGHT", POWER, "#c3c3c3", 736.0, 5.0, 158.0,19.0);

        return farmPane;
    }
}