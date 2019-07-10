package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderPitch_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LShoulderPitch_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderPitch_Up\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LShoulderRoll_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderRoll_Left\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LShoulderRoll_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderRoll_Right\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RShoulderPitch_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderPitch_Up\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RShoulderPitch_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderPitch_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RShoulderRoll_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderRoll_Left\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RShoulderRoll_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderRoll_Right\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(REllbow_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RElbowRoll_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(REllbow_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RElbowRoll_Up\", \"value\":" + arms_speed.getValue() +"}");
		});

		new ButtonLongPress(REllbow_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RElbowYaw_Right\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(REllbow_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RElbowYaw_Left\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LEllbow_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LElbowRoll_Up\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LEllbow_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LElbowRoll_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LEllbow_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LElbowYaw_Right\", \"value\":" + arms_speed.getValue() +"}");
		});

		new ButtonLongPress(LEllbow_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LElbowYaw_Left\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LHand_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LHand_Up\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LHand_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LHand_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LHand_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LWristYaw_Up\", \"value\":" + arms_speed.getValue() + "}");
		});

		new ButtonLongPress(LHand_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LWristYaw_Down\", \"value\":" + arms_speed.getValue() +"}");
		});

		new ButtonLongPress(RHand_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RHand_Up\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHand_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RHand_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHand_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RWristYaw_Up\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHand_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RWristYaw_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
	}
}
