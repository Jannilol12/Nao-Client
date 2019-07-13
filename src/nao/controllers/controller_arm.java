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

public class controller_arm implements Initializable{
	@FXML
    private Slider arms_speed;
	
	@FXML
	private Button LShoulderPitch_Down;
	
	@FXML
	private Button LShoulderPitch_Up;
	
	@FXML
	private Button LShoulderRoll_Left;
	
	@FXML
	private Button LShoulderRoll_Right;
	
	@FXML
	private Button RShoulderPitch_Down;
	
	@FXML
	private Button RShoulderPitch_Up;
	
	@FXML
	private Button RShoulderRoll_Left;
	
	@FXML
	private Button RShoulderRoll_Right;
	
	@FXML
	private Button REllbow_Up;
	
	@FXML
	private Button REllbow_Down;
	
	@FXML
	private Button REllbow_Left;
	
	@FXML
	private Button REllbow_Right;
	
	@FXML
	private Button LEllbow_Up;
	
	@FXML
	private Button LEllbow_Down;
	
	@FXML
	private Button LEllbow_Left;
	
	@FXML
	private Button LEllbow_Right;
	
	@FXML
	private Button LHand_Up;
	
	@FXML
	private Button LHand_Down;
	
	@FXML
	private Button LHand_Left;
	
	@FXML
	private Button LHand_Right;
	
	@FXML
	private Button RHand_Up;
	
	@FXML
	private Button RHand_Down;
	
	@FXML
	private Button RHand_Left;
	
	@FXML
	private Button RHand_Right;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new ButtonLongPress(LShoulderPitch_Down, () -> {
			sender.sendMessage(getJsonString("LShoulderPitch_Down"));
		});
		
		new ButtonLongPress(LShoulderPitch_Up, () -> {
			sender.sendMessage(getJsonString("LShoulderPitch_Up"));
		});
		
		new ButtonLongPress(LShoulderRoll_Left, () -> {
			sender.sendMessage(getJsonString("LShoulderRoll_Left"));
		});
		
		new ButtonLongPress(LShoulderRoll_Right, () -> {
			sender.sendMessage(getJsonString("LShoulderRoll_Right"));
		});
		
		new ButtonLongPress(RShoulderPitch_Up, () -> {
			sender.sendMessage(getJsonString("RShoulderPitch_Up"));
		});
		
		new ButtonLongPress(RShoulderPitch_Down, () -> {
			sender.sendMessage(getJsonString("RShoulderPitch_Down"));
		});
		
		new ButtonLongPress(RShoulderRoll_Left, () -> {
			sender.sendMessage(getJsonString("RShoulderRoll_Left"));
		});
		
		new ButtonLongPress(RShoulderRoll_Right, () -> {
			sender.sendMessage(getJsonString("RShoulderRoll_Right"));
		});
		
		new ButtonLongPress(REllbow_Up, () -> {
			sender.sendMessage(getJsonString("RElbowRoll_Down"));
		});
		
		new ButtonLongPress(REllbow_Down, () -> {
			sender.sendMessage(getJsonString("RElbowRoll_Up"));
		});

		new ButtonLongPress(REllbow_Left, () -> {
			sender.sendMessage(getJsonString("RElbowYaw_Right"));
		});
		
		new ButtonLongPress(REllbow_Right, () -> {
			sender.sendMessage(getJsonString("RElbowYaw_Left"));
		});
		
		new ButtonLongPress(LEllbow_Up, () -> {
			sender.sendMessage(getJsonString("LElbowRoll_Up"));
		});
		
		new ButtonLongPress(LEllbow_Down, () -> {
			sender.sendMessage(getJsonString("LElbowRoll_Down"));
		});
		
		new ButtonLongPress(LEllbow_Left, () -> {
			sender.sendMessage(getJsonString("LElbowYaw_Right"));
		});

		new ButtonLongPress(LEllbow_Right, () -> {
			sender.sendMessage(getJsonString("LElbowYaw_Left"));
		});
		
		new ButtonLongPress(LHand_Up, () -> {
			sender.sendMessage(getJsonString("LHand_Up"));
		});
		
		new ButtonLongPress(LHand_Down, () -> {
			sender.sendMessage(getJsonString("LHand_Down"));
		});
		
		new ButtonLongPress(LHand_Left, () -> {
			sender.sendMessage(getJsonString("LWristYaw_Up"));
		});

		new ButtonLongPress(LHand_Right, () -> {
			sender.sendMessage(getJsonString("LWristYaw_Down"));
		});

		new ButtonLongPress(RHand_Up, () -> {
			sender.sendMessage(getJsonString("RHand_Up"));
		});
		
		new ButtonLongPress(RHand_Down, () -> {
			sender.sendMessage(getJsonString("RHand_Down"));
		});
		
		new ButtonLongPress(RHand_Left, () -> {
			sender.sendMessage(getJsonString("RWristYaw_Up"));
		});
		
		new ButtonLongPress(RHand_Right, () -> {
			sender.sendMessage(getJsonString("RWristYaw_Down"));
		});
	}
	
	private String getJsonString(String motorname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "motors");
		jsonObject.add("value", arms_speed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
