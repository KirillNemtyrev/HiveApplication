package com.example.crypto.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.crypto.WindowPage;
import com.example.crypto.methods.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewFarm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox fieldAutoTags;

    @FXML
    private CheckBox fieldHiveOn;

    @FXML
    private Label fieldMore;

    @FXML
    private TextField fieldName;

    @FXML
    void initialize() {
        eventMouseOnEntered();
        eventMouseOnExited();
        eventMouseOnClicked();
    }

    @FXML
    public void eventMouseOnEntered(){
        fieldMore.setOnMouseEntered(event ->
                fieldMore.setStyle("-fx-text-fill:#094680"));
        btnSave.setOnMouseEntered(event ->
                btnSave.setStyle("-fx-background-color: #22262b"));
        btnCancel.setOnMouseEntered(event ->
                btnCancel.setStyle("-fx-background-color: #22262b"));
    }

    @FXML
    public void eventMouseOnExited(){
        fieldMore.setOnMouseExited(event ->
                fieldMore.setStyle("-fx-text-fill:#1a7cdd"));
        btnSave.setOnMouseExited(event ->
                btnSave.setStyle("-fx-background-color: #c6ccd2"));
        btnCancel.setOnMouseExited(event ->
                btnCancel.setStyle("-fx-background-color: #363d45"));
    }

    @FXML
    public void eventMouseOnClicked(){
        fieldMore.setOnMouseClicked(mouseEvent ->
                WindowPage.openWebpage("https://inlnk.ru/20JvQe"));
        btnCancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });
        btnSave.setOnAction(actionEvent -> {
            String nameFarm = fieldName.getText().trim();

            if(nameFarm.isEmpty()){
                fieldName.setStyle("-fx-background-color: #22262b; -fx-border-color: red");
                return;
            }

            int codeRequest = Request.createFerm(nameFarm, fieldAutoTags.isSelected(), fieldHiveOn.isSelected());
            if(codeRequest != Request.CODE_CREATE_FARM){
                fieldName.setStyle("-fx-background-color: #22262b; -fx-border-color: red");
                return;
            }

            Request.getFarms();

            Stage stage = (Stage) btnSave.getScene().getWindow();
            WindowPage.updateWindow((Stage) stage.getOwner(), "Главная", "farms.fxml", 950, 665,false);
            stage.close();
        });
    }

}
