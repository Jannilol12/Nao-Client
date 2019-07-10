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
		
		new ButtonLongPress(LShoulderPitch_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderPitch_Down\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RShoulderRoll_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderRoll_Left\", \"value\":" + arms_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RShoulderRoll_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderRoll_Right\", \"value\":" + arms_speed.getValue() +"}");
		});
		
	}
}
