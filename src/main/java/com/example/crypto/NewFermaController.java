package com.example.crypto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewFermaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane clickedCopy;

    @FXML
    private AnchorPane clickedImage;

    @FXML
    private CheckBox fieldAutoTags;

    @FXML
    private Label fieldHiveon;

    @FXML
    private TextField fieldName;

    @FXML
    private Label fieldMore;

    @FXML
    void initialize() {
        // Action mouse on Author Field
        fieldMore.setOnMouseEntered(event -> fieldMore.setStyle("-fx-text-fill:#094680"));
        fieldMore.setOnMouseExited(event -> fieldMore.setStyle("-fx-text-fill:#1a7cdd"));
        fieldMore.setOnMouseClicked(mouseEvent -> WindowPage.openWebpage("https://inlnk.ru/20JvQe"));
        // Action mouse on btn Save
        btnSave.setOnMouseEntered(event -> btnSave.setStyle("-fx-background-color: #22262b"));
        btnSave.setOnMouseExited(event -> btnSave.setStyle("-fx-background-color: #c6ccd2"));
        // Action mouse on btn Cancel
        btnCancel.setOnMouseEntered(event -> btnCancel.setStyle("-fx-background-color: #22262b"));
        btnCancel.setOnMouseExited(event -> btnCancel.setStyle("-fx-background-color: #363d45"));
        btnCancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });
    }

}