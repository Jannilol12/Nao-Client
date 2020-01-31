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

/**
 * Control the Head of the nao
 */
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

	/**
	 * Initialize is like a constructor for JavaFX
	 * @param arg0 never used
	 * @param arg1 never used
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//When pressing a button long, normally its method is called only once, so we wrote our own Listener

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

	/**
	 * Generate the JSONObject with all information for sending it to the robot
	 * @param motorname which motor is used
	 * @return return a JSONObject with the motor name and the speed for the motors
	 */
	private String getJsonString(String motorname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Motors");
		jsonObject.add("value", headSpeed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
