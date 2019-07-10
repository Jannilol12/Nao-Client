package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import nao.events.ButtonLongPress;
import nao.sender;

public class controller_leg implements Initializable{
	@FXML
    private Slider leg_speed;
	
	@FXML
    private Button LHip_Up;

    @FXML
    private Button LHip_Down;

    @FXML
    private Button LHip_Left;

    @FXML
    private Button LHip_Right;

    @FXML
    private Button RHip_Up;

    @FXML
    private Button RHip_Down;

    @FXML
    private Button RHip_Left;

    @FXML
    private Button RHip_Right;

    @FXML
    private Button RKnee_Up;

    @FXML
    private Button RKnee_Down;

    @FXML
    private Button LKnee_Up;

    @FXML
    private Button LKnee_Down;

    @FXML
    private Button LAnkle_Up;

    @FXML
    private Button LAnkle_Down;

    @FXML
    private Button LAnkle_Left;

    @FXML
    private Button LAnkle_Right;

    @FXML
    private Button RAnkle_Up;

    @FXML
    private Button RAnkle_Down;

    @FXML
    private Button RAnkle_Left;

    @FXML
    private Button RAnkle_Right;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		new ButtonLongPress(LHip_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LHipPitch_Up\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LHip_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LHipPitch_Down\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LHip_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LHipRoll_Left\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LHip_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LHipRoll_Right\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHip_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RHipPitch_Up\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHip_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RHipPitch_Down\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHip_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RHipRoll_Left\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RHip_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RHipRoll_Right\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RKnee_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RKneePitch_Up\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RKnee_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RKneePitch_Down\", \"value\":" + leg_speed.getValue() + "}");
		});
		new ButtonLongPress(LKnee_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LKneePitch_Up\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LKnee_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LKneePitch_Down\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LAnkle_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LAnklePitch_Up\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LAnkle_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LAnklePitch_Down\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LAnkle_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LAnkleRoll_Left\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(LAnkle_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LAnkleRoll_Right\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RAnkle_Up, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RAnklePitch_Up\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RAnkle_Down, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RAnklePitch_Down\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RAnkle_Left, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RAnkleRoll_Left\", \"value\":" + leg_speed.getValue() +"}");
		});
		
		new ButtonLongPress(RAnkle_Right, () -> {
			sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RAnkleRoll_Right\", \"value\":" + leg_speed.getValue() +"}");
		});
	}
}
