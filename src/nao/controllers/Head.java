package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import nao.sender;
import nao.events.ButtonLongPress;

public class Head implements Initializable{
	@FXML
    private Slider headSpeed;
	
	@FXML
	private Button headPitchDown;
	
	@FXML
	private Button headPitchUp;
	
	@FXML
	private Button headYawLeft;
	
	@FXML
	private Button headYawRight;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new ButtonLongPress(headPitchDown, () -> {
			sender.sendMessage(getJsonString("headPitchDown"));
		});
		
		new ButtonLongPress(headPitchUp, () -> {
			sender.sendMessage(getJsonString("headPitchUp"));
		});
		
		new ButtonLongPress(headYawLeft, () -> {
			sender.sendMessage(getJsonString("headYawLeft"));
		});
		
		new ButtonLongPress(headYawRight, () -> {
			sender.sendMessage(getJsonString("headYawRight"));
		});
	}
	
	private String getJsonString(String motorname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Motors");
		jsonObject.add("value", headSpeed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
