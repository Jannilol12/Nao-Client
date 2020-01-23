package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nao.sender;

public class Move implements Initializable {
	@FXML
    private TextField Steps;

    @FXML
    private CheckBox checkSteps;
    
    @FXML
    private TextField degree;

    @FXML
    private ComboBox<String> postureSelect;

    @FXML
    private TextField PostureSpeed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postureSelect.getItems().addAll("Stand", "StandInit", "StandZero", "Crouch", "Sit", "SitRelax", "LyingBelly", "LyingBack");
    }

    @FXML
    void wakeUp(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Wakeup\"}");
    }
	
	@FXML
    void forward(ActionEvent event) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Forward");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }
	
	@FXML
    void backwards(ActionEvent event) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Backward");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void left(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Left");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void right(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Right");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void stop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Stop");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void rotate(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Rotate");
        jsonObject.add("value", Integer.parseInt(degree.getText()));
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void PostureStart(ActionEvent event) {
        postureSelect.getSelectionModel().clearSelection();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Posture");
        jsonObject.add("position", postureSelect.getSelectionModel().getSelectedItem());
        jsonObject.add("speed", Float.parseFloat(PostureSpeed.getText()));
        sender.sendMessage(jsonObject.toJSONString());
    }
}
