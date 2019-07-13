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
			sender.sendMessage(getJsonString("LHipPitch_Up"));
		});
		
		new ButtonLongPress(LHip_Down, () -> {
			sender.sendMessage(getJsonString("LHipPitch_Down"));
		});
		
		new ButtonLongPress(LHip_Left, () -> {
			sender.sendMessage(getJsonString("LHipRoll_Left"));
		});
		
		new ButtonLongPress(LHip_Right, () -> {
			sender.sendMessage(getJsonString("LHipRoll_Right"));
		});
		
		new ButtonLongPress(RHip_Up, () -> {
			sender.sendMessage(getJsonString("RHipPitch_Up"));
		});
		
		new ButtonLongPress(RHip_Down, () -> {
			sender.sendMessage(getJsonString("RHipPitch_Down"));
		});
		
		new ButtonLongPress(RHip_Left, () -> {
			sender.sendMessage(getJsonString("RHipRoll_Left"));
		});
		
		new ButtonLongPress(RHip_Right, () -> {
			sender.sendMessage(getJsonString("RHipRoll_Right"));
		});
		
		new ButtonLongPress(RKnee_Up, () -> {
			sender.sendMessage(getJsonString("RKneePitch_Up"));
		});
		
		new ButtonLongPress(RKnee_Down, () -> {
			sender.sendMessage(getJsonString("RKneePitch_Down"));
		});
		new ButtonLongPress(LKnee_Up, () -> {
			sender.sendMessage(getJsonString("LKneePitch_Up"));
		});
		
		new ButtonLongPress(LKnee_Down, () -> {
			sender.sendMessage(getJsonString("LKneePitch_Down"));
		});
		
		new ButtonLongPress(LAnkle_Up, () -> {
			sender.sendMessage(getJsonString("LAnklePitch_Up"));
		});
		
		new ButtonLongPress(LAnkle_Down, () -> {
			sender.sendMessage(getJsonString("LAnklePitch_Down"));
		});
		
		new ButtonLongPress(LAnkle_Left, () -> {
			sender.sendMessage(getJsonString("LAnkleRoll_Left"));
		});
		
		new ButtonLongPress(LAnkle_Right, () -> {
			sender.sendMessage(getJsonString("LAnkleRoll_Right"));
		});
		
		new ButtonLongPress(RAnkle_Up, () -> {
			sender.sendMessage(getJsonString("RAnklePitch_Up"));
		});
		
		new ButtonLongPress(RAnkle_Down, () -> {
			sender.sendMessage(getJsonString("RAnklePitch_Down"));
		});
		
		new ButtonLongPress(RAnkle_Left, () -> {
			sender.sendMessage(getJsonString("RAnkleRoll_Left"));
		});
		
		new ButtonLongPress(RAnkle_Right, () -> {
			sender.sendMessage(getJsonString("RAnkleRoll_Right"));
		});
	}
	
	private String getJsonString(String motorname) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "motors");
		jsonObject.add("value", leg_speed.getValue());
		jsonObject.add("motorname", motorname);
		return jsonObject.toJSONString();
	}
}
