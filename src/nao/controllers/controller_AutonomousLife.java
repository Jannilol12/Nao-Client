package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

public class controller_AutonomousLife implements Initializable {
    @FXML
    private ComboBox<String> LifeMode;

    @FXML
    private TextField OffSetFromFloor;

    @FXML
    private Text ActualLifeMode;

    @FXML
    private Text OffsetFromFloor;

    @FXML
    private Text ExpressiveListening;

    @FXML
    private Text BackgroundStrategy;

    @FXML
    private ComboBox<String> StrategyList;

    @FXML
    private Button SetExpressiveListeningButton;

    @FXML
    void SetBackgroundStrategy(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "AutoLife");
        jsonObject.add("function", "BackgroundStrategy");
        jsonObject.add("Strategy", StrategyList.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

    private boolean isSetExpressiveListeningSelected = false;

    @FXML
    void SetExpressiveListening(ActionEvent event) {
        if(isSetExpressiveListeningSelected){
            SetExpressiveListeningButton.setStyle("-fx-background-color: Red");
            isSetExpressiveListeningSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "ExpressiveListening");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            SetExpressiveListeningButton.setStyle("-fx-background-color: Green");
            isSetExpressiveListeningSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "ExpressiveListening");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void SetLifeMode(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "AutoLife");
        jsonObject.add("function", "LifeMode");
        jsonObject.add("Mode",  LifeMode.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void setRobotOffsetFromFloor(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "AutoLife");
        jsonObject.add("function", "RobotOffsetFromFloor");
        jsonObject.add("Offset",  Float.parseFloat(OffSetFromFloor.getText()));
        sender.sendMessage(jsonObject.toJSONString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LifeMode.getItems().add("solitary");
        LifeMode.getItems().add("interactive");
        LifeMode.getItems().add("disabled");
        LifeMode.getItems().add("safeguard");

        StrategyList.getItems().add("none");
        StrategyList.getItems().add("backToNeutral");
    }
}
