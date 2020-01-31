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
 * Control the arms of the robot
 */
public class Arms implements Initializable{
	@FXML
    private Slider armsSpeed;
	
	@FXML
	private Button lShoulderPitchDown;
	
	@FXML
	private Button lShoulderPitchUp;
	
	@FXML
	private Button lShoulderRollLeft;
	
	@FXML
	private Button lShoulderRollRight;
	
	@FXML
	private Button rShoulderPitchDown;
	
	@FXML
	private Button rShoulderPitchUp;
	
	@FXML
	private Button rShoulderRollLeft;
	
	@FXML
	private Button rShoulderRollRight;
	
	@FXML
	private Button rEllbowUp;
	
	@FXML
	private Button rEllbowDown;
	
	@FXML
	private Button rEllbowLeft;
	
	@FXML
	private Button rEllbowRight;
	
	@FXML
	private Button lEllbowUp;
	
	@FXML
	private Button lEllbowDown;
	
	@FXML
	private Button lEllbowLeft;
	
	@FXML
	private Button lEllbowRight;
	
	@FXML
	private Button lHandUp;
	
	@FXML
	private Button lHandDown;
	
	@FXML
	private Button lHandLeft;
	
	@FXML
	private Button lHandRight;
	
	@FXML
	private Button rHandUp;
	
	@FXML
	private Button rHandDown;
	
	@FXML
	private Button rHandLeft;
	
	@FXML
	private Button rHandRight;

	/**
	 * Initialize is like a constructor for JavaFX
	 * @param arg0 never used
	 * @param arg1 never used
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//When pressing a button long, normally its method is called only once, so we wrote our own Listener
		new ButtonLongPress(lShoulderPitchDown, () -> {
			sender.sendMessage(getJsonString("lShoulderPitchDown"));
		});
		
		new ButtonLongPress(lShoulderPitchUp, () -> {
			sender.sendMessage(getJsonString("lShoulderPitchUp"));
		});
		
		new ButtonLongPress(lShoulderRollLeft, () -> {
			sender.sendMessage(getJsonString("lShoulderRollLeft"));
		});
		
		new ButtonLongPress(lShoulderRollRight, () -> {
			sender.sendMessage(getJsonString("lShoulderRollRight"));
		});
		
		new ButtonLongPress(rShoulderPitchUp, () -> {
			sender.sendMessage(getJsonString("rShoulderPitchUp"));
		});
		
		new ButtonLongPress(rShoulderPitchDown, () -> {
			sender.sendMessage(getJsonString("rShoulderPitchDown"));
		});
		
		new ButtonLongPress(rShoulderRollLeft, () -> {
			sender.sendMessage(getJsonString("rShoulderRollLeft"));
		});
		
		new ButtonLongPress(rShoulderRollRight, () -> {
			sender.sendMessage(getJsonString("rShoulderRollRight"));
		});
		
		new ButtonLongPress(rEllbowUp, () -> {
			sender.sendMessage(getJsonString("rElbowRollDown"));
		});
		
		new ButtonLongPress(rEllbowDown, () -> {
			sender.sendMessage(getJsonString("rElbowRollUp"));
		});

		new ButtonLongPress(rEllbowLeft, () -> {
			sender.sendMessage(getJsonString("rElbowYawRight"));
		});
		
		new ButtonLongPress(rEllbowRight, () -> {
			sender.sendMessage(getJsonString("rElbowYawLeft"));
		});
		
		new ButtonLongPress(lEllbowUp, () -> {
			sender.sendMessage(getJsonString("lElbowRollUp"));
		});
		
		new ButtonLongPress(lEllbowDown, () -> {
			sender.sendMessage(getJsonString("lElbowRollDown"));
		});
		
		new ButtonLongPress(lEllbowLeft, () -> {
			sender.sendMessage(getJsonString("lElbowYawRight"));
		});

		new ButtonLongPress(lEllbowRight, () -> {
			sender.sendMessage(getJsonString("lElbowYawLeft"));
		});
		
		new ButtonLongPress(lHandUp, () -> {
			sender.sendMessage(getJsonString("lHandUp"));
		});
		
		new ButtonLongPress(lHandDown, () -> {
			sender.sendMessage(getJsonString("lHandDown"));
		});
		
		new ButtonLongPress(lHandLeft, () -> {
			sender.sendMessage(getJsonString("lWristYawUp"));
		});

		new ButtonLongPress(lHandRight, () -> {
			sender.sendMessage(getJsonString("lWristYawDown"));
		});

		new ButtonLongPress(rHandUp, () -> {
			sender.sendMessage(getJsonString("rHandUp"));
		});
		
		new ButtonLongPress(rHandDown, () -> {
			sender.sendMessage(getJsonString("rHandDown"));
		});
		
		new ButtonLongPress(rHandLeft, () -> {
			sender.sendMessage(getJsonString("rWristYawUp"));
		});
		
		new ButtonLongPress(rHandRight, () -> {
			sender.sendMessage(getJsonString("rWristYawDown"));
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
		jsonObject.add("value", armsSpeed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
