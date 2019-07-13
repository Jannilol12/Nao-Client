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

public class controller_walk implements Initializable {
	@FXML
    private TextField Steps;

    @FXML
    private CheckBox checkSteps;
    
    @FXML
    private TextField degree;

    @FXML
    private ComboBox<String> PostureBox;

    @FXML
    private TextField PostureSpeed;

    @FXML
    void Wake_Up(ActionEvent event) {
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
		jsonObject.add("type", "Backwards");
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
        sender.sendMessage("{\"type\":\"Stop\", \"value\":\"0\"}");
    }

    @FXML
    void rotate(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Rotate\", \"value\":\"" + Integer.parseInt(degree.getText()) +"\"}");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	PostureBox.getItems().addAll("Stand", "StandInit", "StandZero", "Crouch", "Sit", "SitRelax", "LyingBelly", "LyingBack");
    }

    @FXML
    void PostureStart(ActionEvent event) {
        sender.sendMessage("{\"type\":\"posture\", \"position\": \"" + PostureBox.getSelectionModel().getSelectedItem() + "\", \"speed\":" + Float.parseFloat(PostureSpeed.getText()) + "}");
    }
}
