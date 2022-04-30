package com.example.crypto;

import java.net.URL;
import java.security.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Notifications {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pagination PageCount;

    @FXML
    private Label btnAccount;

    @FXML
    private Label btnChangeUser;

    @FXML
    private Label btnFarm;

    @FXML
    private Label btnNotifications;

    @FXML
    private Label btnPayment;

    @FXML
    private Label btnReferals;

    @FXML
    private Label btnSignOut;

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

    private JSONObject getDate;
    private JSONArray getNotifications;

    private int countNotifications;

    @FXML
    void initialize() {

        //Date date = new Date();
        //int currentHour = date.getHours();

        getDate = HTTPRequests.GetEvents();
        getNotifications = (JSONArray) getDate.get("data");
        countNotifications = getNotifications.size();

        if(countNotifications != 0) {
            PageCount.setVisible(true);
            PageCount.setPageCount((int) Math.ceil((double) countNotifications/9.0));
        }

        /*JSONObject profile = (JSONObject) getAccount.get("profile");
        fieldBalance.setText("$ " + getAccount.get("balance"));
        fieldName.setText((String) profile.get("login"));
        fieldFarms.setText(String.valueOf(countFarms));

        String welcomeMessage = "Доброе утро, " + profile.get("login") + "!";
        if(currentHour >= 12 && currentHour < 18) welcomeMessage = "Добрый день, " + profile.get("login") + "!";
        else if(currentHour >= 18 && currentHour < 24) welcomeMessage = "Добрый вечер, " + profile.get("login") + "!";
        else if(currentHour >= 0 && currentHour < 6) welcomeMessage = "Доброй ночи, " + profile.get("login") + "!";
        fieldName.setText(welcomeMessage);*/

        fieldImage.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://hiveon.com/"));
        fieldGitHub.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://github.com/KirillNemtyrev/crypto"));

        // Menu page
        btnFarm.setOnMouseEntered(mouseEvent -> btnFarm.setTextFill(Paint.valueOf("#656060")));
        btnFarm.setOnMouseExited(mouseEvent -> btnFarm.setTextFill(Paint.valueOf("#9e9e9e")));
        btnFarm.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) btnFarm.getScene().getWindow();
            WindowPage.updateWindow(stage, "Главная", "main_activity.fxml", 950, 665,false);
        });

        btnAccount.setOnMouseEntered(mouseEvent -> btnAccount.setTextFill(Paint.valueOf("#656060")));
        btnAccount.setOnMouseExited(mouseEvent -> btnAccount.setTextFill(Paint.valueOf("#9e9e9e")));
        btnReferals.setOnMouseEntered(mouseEvent -> btnReferals.setTextFill(Paint.valueOf("#656060")));
        btnReferals.setOnMouseExited(mouseEvent -> btnReferals.setTextFill(Paint.valueOf("#9e9e9e")));
        btnPayment.setOnMouseEntered(mouseEvent -> btnPayment.setTextFill(Paint.valueOf("#656060")));
        btnPayment.setOnMouseExited(mouseEvent -> btnPayment.setTextFill(Paint.valueOf("#9e9e9e")));
        btnNotifications.setOnMouseEntered(mouseEvent -> btnNotifications.setTextFill(Paint.valueOf("#656060")));
        btnNotifications.setOnMouseExited(mouseEvent -> btnNotifications.setTextFill(Paint.valueOf("#9e9e9e")));
        btnNotifications.setOnMouseClicked(mouseEvent -> System.out.print(HTTPRequests.GetEvents()));

        btnChangeUser.setOnMouseEntered(mouseEvent -> btnChangeUser.setTextFill(Paint.valueOf("#656060")));
        btnChangeUser.setOnMouseExited(mouseEvent -> btnChangeUser.setTextFill(Paint.valueOf("#9e9e9e")));
        btnChangeUser.setOnMouseClicked(mouseEvent -> {
            HTTPRequests.Logout();
            ConfigFile.setPASSWORD("");
            ConfigFile.setLOGIN("");
            ConfigFile.setSave(false);
            ConfigFile.SaveCFG();

            Stage stage = (Stage) btnChangeUser.getScene().getWindow();
            WindowPage.updateWindow(stage, "Авторизация", "auth.fxml", 600, 400);
        });

        btnSignOut.setOnMouseEntered(mouseEvent -> btnSignOut.setTextFill(Paint.valueOf("#542323")));
        btnSignOut.setOnMouseExited(mouseEvent -> btnSignOut.setTextFill(Paint.valueOf("#943e3e")));
        btnSignOut.setOnMouseClicked(mouseEvent -> {
            HTTPRequests.Logout();
            ConfigFile.setPASSWORD("");
            ConfigFile.setLOGIN("");
            ConfigFile.setSave(false);
            ConfigFile.SaveCFG();

            System.exit(0);
        });

        PageCount.setPageFactory((pageIndex) -> {

            int count = 1;
            AnchorPane PagePane = createActivePane();
            while (4*pageIndex + count <= countNotifications && count <= 9){

                final double posX = 25.0; // default
                double posY = 15.0 + 50.0*(count - 1);
                int index = 9*pageIndex + (count - 1);

                //addList(posX, posY, index);
                PagePane.getChildren().add(addList(posX, posY, index));
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
        ListPane.setPrefWidth(901.0);
        ListPane.setPrefHeight(34.0);
        ListPane.setStyle("-fx-background-color: #2f353c");

        return ListPane;
    }

    @FXML
    public void addLabel(AnchorPane ListPane, String Aligmnment,
                         String name, String color, double posX, double posY, double prefWidth, double prefHeight) {
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
    public AnchorPane addList(double posX, double posY, int index){

        JSONObject notification = (JSONObject) getNotifications.get(index);
        AnchorPane notificationPane = createPane(posX, posY);

        String ACTION = String.valueOf(notification.get("type"));
        addLabel(notificationPane, "", ACTION, "#c3c3c3", 14.0, 8.0, 266.0,18.0);

        String IP = String.valueOf(notification.get("ip"));
        addLabel(notificationPane, "", IP, "#c3c3c3", 392.0, 8.0, 138.0,18.0);

        //Timestamp timestamp = (Timestamp) notification.get("timestamp");

        //Date date = new Date(timestamp.getTimestamp());
        //String TIME = timestamp.getTimestamp().toString();
        addLabel(notificationPane, "CENTER_RIGHT", "TIME", "#c3c3c3", 621.0, 8.0, 266.0,19.0);

        return notificationPane;
    }

}