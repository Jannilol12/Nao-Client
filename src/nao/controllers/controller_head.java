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

public class controller_head implements Initializable{
	@FXML
    private Slider head_speed;
	
	@FXML
	private Button HeadPitch_Down;
	
	@FXML
	private Button HeadPitch_Up;
	
	@FXML
	private Button HeadYaw_Left;
	
	@FXML
	private Button HeadYaw_Right;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new ButtonLongPress(HeadPitch_Down, () -> {
			sender.sendMessage(getJsonString("HeadPitch_Down"));
		});
		
		new ButtonLongPress(HeadPitch_Up, () -> {
			sender.sendMessage(getJsonString("HeadPitch_Up"));
		});
		
		new ButtonLongPress(HeadYaw_Left, () -> {
			sender.sendMessage(getJsonString("HeadYaw_Left"));
		});
		
		new ButtonLongPress(HeadYaw_Right, () -> {
			sender.sendMessage(getJsonString("HeadYaw_Right"));
		});
	}
	
	private String getJsonString(String motorname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "motors");
		jsonObject.add("value", head_speed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
