package com.example.crypto.controllers;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.time.LocalTime;
import java.time.ZoneId;

public class MineCost {

    @FXML
    private Pagination PageCount;

    @FXML
    private Label btnAccount;

    @FXML
    private Label btnChangeUser;

    @FXML
    private Label btnCost;

    @FXML
    private Label btnFarms;

    @FXML
    private Label btnList;

    @FXML
    private Label btnMain;

    @FXML
    private Button btnParse;

    @FXML
    private Label btnPayment;

    @FXML
    private Label btnSignOut;

    @FXML
    private Label btnTemplates;

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
    private TextField fieldPower;

    @FXML
    void initialize() {
        eventChangeText();

        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();

        int count = (int) Math.ceil((double) ParseData.getCountCard()/5.0);
        PageCount.setPageCount(count);
        setupPagination();
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

        btnFarms.setOnMouseEntered(mouseEvent ->
                btnFarms.setTextFill(Paint.valueOf("#656060")));
        btnTemplates.setOnMouseEntered(mouseEvent ->
                btnTemplates.setTextFill(Paint.valueOf("#656060")));
        btnList.setOnMouseEntered(mouseEvent ->
                btnList.setTextFill(Paint.valueOf("#656060")));
        btnCost.setOnMouseEntered(mouseEvent ->
                btnCost.setTextFill(Paint.valueOf("#656060")));

        btnParse.setOnMouseEntered(event ->
                btnParse.setStyle("-fx-background-color: #8d949a"));
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

        btnFarms.setOnMouseExited(mouseEvent ->
                btnFarms.setTextFill(Paint.valueOf("#9e9e9e")));
        btnTemplates.setOnMouseExited(mouseEvent ->
                btnTemplates.setTextFill(Paint.valueOf("#9e9e9e")));
        btnList.setOnMouseExited(mouseEvent ->
                btnList.setTextFill(Paint.valueOf("#9e9e9e")));
        btnCost.setOnMouseExited(mouseEvent ->
                btnCost.setTextFill(Paint.valueOf("#9e9e9e")));

        btnParse.setOnMouseExited(event ->
                btnParse.setStyle("-fx-background-color: #c6ccd2"));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldImage.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://hiveon.com/"));
        fieldGitHub.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://github.com/KirillNemtyrev/crypto"));

        btnAccount.setOnMouseClicked(mouseEvent ->{
            Stage stage = (Stage) btnAccount.getScene().getWindow();
            WindowPage.updateWindow(stage, "Аккаунт", "profile.fxml", 950, 665, false);
        });
        btnChangeUser.setOnMouseClicked(mouseEvent -> {
            Request.Logout();
            Settings.setSettingToken(null);
            Settings.saveParams();

            Stage stage = (Stage) btnChangeUser.getScene().getWindow();
            WindowPage.updateWindow(stage, "Авторизация", "auth.fxml", 678, 469);
        });
        btnSignOut.setOnMouseClicked(mouseEvent -> {
            Request.Logout();
            Settings.setSettingToken(null);
            Settings.saveParams();

            System.exit(0);
        });
        btnFarms.setOnMouseClicked(mouseEvent ->{
            Stage stage = (Stage) btnFarms.getScene().getWindow();
            WindowPage.updateWindow(stage, "Фермы", "farms.fxml", 950, 665, false);
        });
        btnParse.setOnMouseClicked(mouseEvent -> {
            String power = fieldPower.getText().trim();
            double cost = Double.parseDouble(power);

            ParseData.setPowerCost(cost);
            ParseData.parseVideo();

            Stage stage = (Stage) btnParse.getScene().getWindow();
            WindowPage.updateWindow(stage, "Криптовалюта", "minecost.fxml", 950, 665, false);
        });
    }

    @FXML
    public void eventChangeText(){
        String balance = Account.getBalance() + " $";
        fieldBalance.setText(balance);
        fieldPower.setText(String.valueOf(ParseData.getPowerCost()));

        // Get time
        ZoneId zoneId = ZoneId.systemDefault();
        LocalTime time = LocalTime.now(zoneId);
        int hour = time.getHour();
        String welcome = (hour < 6 ? "Доброй ночи" : hour < 12 ? "Доброго утра" :
                hour < 18 ? "Доброго дня" : "Доброго вечера") + " , " + Account.getLogin();

        fieldName.setText(welcome);
        fieldFarms.setText(String.valueOf(Farm.getCount_farm()));
    }

    @FXML
    public void setupPagination(){
        PageCount.setPageFactory((pageIndex) -> {
            int count = 1;
            AnchorPane PagePane = createActivePane();
            while (5*pageIndex + count <= ParseData.getCountCard() && count <= 5){

                final double posX = 24; // default
                double posY = 216.0 + 75.0*(count - 1);
                int index = 5*pageIndex + (count - 1);

                //addList(posX, posY, index);
                PagePane.getChildren().add(insertCard(posX, posY, index));
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
    public AnchorPane createPane(double posX, double posY, int index) {

        AnchorPane ListPane = new AnchorPane();
        ListPane.setLayoutX(posX);
        ListPane.setLayoutY(posY);
        ListPane.setPrefWidth(890);
        ListPane.setPrefHeight(67);
        ListPane.setCursor(Cursor.cursor("HAND"));
        ListPane.setStyle("-fx-background-color: #2f353c");

        ListPane.setOnMouseEntered(event -> ListPane.setStyle("-fx-background-color: #192128"));
        ListPane.setOnMouseExited(event -> ListPane.setStyle("-fx-background-color: #2f353c"));
        ListPane.setOnMouseClicked(mouseEvent -> {
            JSONObject data = ParseData.getData(index);
            WindowPage.openWebpage((String) data.get("link"));
        });

        return ListPane;
    }

    @FXML
    public void addLabel(AnchorPane ListPane, String Aligmnment, String name, String color, double posX, double posY,
                         double prefWidth, double prefHeight) {
        addLabel(ListPane, Aligmnment, name, color, posX, posY, prefWidth, prefHeight, 14);
    }

    @FXML
    public void addLabel(AnchorPane ListPane, String Aligmnment, String name, String color, double posX, double posY,
                         double prefWidth, double prefHeight, double size) {
        Label Name = new Label(name);
        Name.setFont(Font.font("Consolas Bold", size));
        Name.setTextFill(Paint.valueOf(color));
        Name.setLayoutX(posX);
        Name.setLayoutY(posY);
        Name.setPrefWidth(prefWidth);
        Name.setPrefHeight(prefHeight);
        if(!Aligmnment.isEmpty()) Name.setAlignment(Pos.valueOf(Aligmnment));
        ListPane.getChildren().add(Name);
    }

    @FXML
    public AnchorPane insertCard(double posX, double posY, int index){

        JSONObject data = ParseData.getData(index);

        AnchorPane minePane = createPane(posX, posY, index);

        String BRAND = String.valueOf(data.get("brand"));
        String BRAND_COLOR = BRAND.equals("NVIDIA") ? "#3ba44f": "#ee1818";
        addLabel(minePane, "", BRAND, BRAND_COLOR, 14, 14, 75,21);

        String NAME = String.valueOf(data.get("name"));
        addLabel(minePane, "", NAME, "#c3c3c3", 14, 34, 228,21);

        String RELEASE = String.valueOf(data.get("release"));
        addLabel(minePane, "CENTER", RELEASE, "#c3c3c3", 261, 15, 107,21);
        addLabel(minePane, "", "Дата выпуска", "#c3c3c3", 259, 36, 107,18);

        String REVENUE = String.valueOf(data.get("revenue"));
        addLabel(minePane, "CENTER", REVENUE, "#c3c3c3", 397, 15, 120,21);
        addLabel(minePane, "", "Доход 24 часа", "#c3c3c3", 400, 36, 120,18);

        String PROFIT = String.valueOf(data.get("profit"));
        addLabel(minePane, "CENTER", PROFIT, "#c3c3c3", 567, 15, 113,21);
        addLabel(minePane, "", "Прибыль 24 часа", "#c3c3c3", 554, 36, 138,18);

        JSONArray coins = (JSONArray) data.get("coins");
        JSONObject first_coin = (JSONObject) coins.get(0);
        JSONObject second_coin = (JSONObject) coins.get(1);
        JSONObject third_coin = (JSONObject) coins.get(2);

        String FIRST_COIN = first_coin.get("name") + " - " + first_coin.get("cost");
        addLabel(minePane, "", FIRST_COIN, "#c3c3c3", 736, 4, 127,11);

        String SECOND_COIN = second_coin.get("name") + " - " + second_coin.get("cost");
        addLabel(minePane, "", SECOND_COIN, "#c3c3c3", 736, 23, 127,11);

        String THIRD_COIN = third_coin.get("name") + " - " + third_coin.get("cost");
        addLabel(minePane, "", THIRD_COIN, "#c3c3c3", 736, 42, 127,11);

        return minePane;
    }
}