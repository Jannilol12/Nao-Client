package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import nao.events.ButtonLongPress;
import nao.sender;

public class Legs implements Initializable{
	@FXML
    private Slider legsSpeed;
	
	@FXML
    private Button lHipUp;

    @FXML
    private Button lHipDown;

    @FXML
    private Button lHipLeft;

    @FXML
    private Button lHipRight;

    @FXML
    private Button rHipUp;

    @FXML
    private Button rHipDown;

    @FXML
    private Button rHipLeft;

    @FXML
    private Button rHipRight;

    @FXML
    private Button rKneeUp;

    @FXML
    private Button rKneeDown;

    @FXML
    private Button lKneeUp;

    @FXML
    private Button lKneeDown;

    @FXML
    private Button lAnkleUp;

    @FXML
    private Button lAnkleDown;

    @FXML
    private Button lAnkleLeft;

    @FXML
    private Button lAnkleRight;

    @FXML
    private Button rAnkleUp;

    @FXML
    private Button rAnkleDown;

    @FXML
    private Button rAnkleLeft;

    @FXML
    private Button rAnkleRight;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		new ButtonLongPress(lHipUp, () -> {
			sender.sendMessage(getJsonString("lHipPitchUp"));
		});
		
		new ButtonLongPress(lHipDown, () -> {
			sender.sendMessage(getJsonString("lHipPitchDown"));
		});
		
		new ButtonLongPress(lHipLeft, () -> {
			sender.sendMessage(getJsonString("lHipRollLeft"));
		});
		
		new ButtonLongPress(lHipRight, () -> {
			sender.sendMessage(getJsonString("lHipRollRight"));
		});
		
		new ButtonLongPress(rHipUp, () -> {
			sender.sendMessage(getJsonString("rHipPitchUp"));
		});
		
		new ButtonLongPress(rHipDown, () -> {
			sender.sendMessage(getJsonString("rHipPitchDown"));
		});
		
		new ButtonLongPress(rHipLeft, () -> {
			sender.sendMessage(getJsonString("rHipRollLeft"));
		});
		
		new ButtonLongPress(rHipRight, () -> {
			sender.sendMessage(getJsonString("rHipRollRight"));
		});
		
		new ButtonLongPress(rKneeUp, () -> {
			sender.sendMessage(getJsonString("rKneePitchUp"));
		});
		
		new ButtonLongPress(rKneeDown, () -> {
			sender.sendMessage(getJsonString("rKneePitchDown"));
		});
		new ButtonLongPress(lKneeUp, () -> {
			sender.sendMessage(getJsonString("lKneePitchUp"));
		});
		
		new ButtonLongPress(lKneeDown, () -> {
			sender.sendMessage(getJsonString("lKneePitchDown"));
		});
		
		new ButtonLongPress(lAnkleUp, () -> {
			sender.sendMessage(getJsonString("lAnklePitchUp"));
		});
		
		new ButtonLongPress(lAnkleDown, () -> {
			sender.sendMessage(getJsonString("lAnklePitchDown"));
		});
		
		new ButtonLongPress(lAnkleLeft, () -> {
			sender.sendMessage(getJsonString("lAnkleRollLeft"));
		});
		
		new ButtonLongPress(lAnkleRight, () -> {
			sender.sendMessage(getJsonString("lAnkleRollRight"));
		});
		
		new ButtonLongPress(rAnkleUp, () -> {
			sender.sendMessage(getJsonString("rAnklePitchUp"));
		});
		
		new ButtonLongPress(rAnkleDown, () -> {
			sender.sendMessage(getJsonString("rAnklePitchDown"));
		});
		
		new ButtonLongPress(rAnkleLeft, () -> {
			sender.sendMessage(getJsonString("rAnkleRollLeft"));
		});
		
		new ButtonLongPress(rAnkleRight, () -> {
			sender.sendMessage(getJsonString("rAnkleRollRight"));
		});
	}
	
	private String getJsonString(String motorname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Motors");
		jsonObject.add("value", legsSpeed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
