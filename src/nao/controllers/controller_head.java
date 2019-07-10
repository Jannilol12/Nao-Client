package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadPitch_Down\", \"value\":" + head_speed.getValue() +"}");
		});
		
		new ButtonLongPress(HeadPitch_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadPitch_Up\", \"value\":" + head_speed.getValue() +"}");
		});
		
		new ButtonLongPress(HeadYaw_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadYaw_Left\", \"value\":" + head_speed.getValue() +"}");
		});
		
		new ButtonLongPress(HeadYaw_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadYaw_Right\", \"value\":" + head_speed.getValue() +"}");
		});
	}
}
