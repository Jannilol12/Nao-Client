package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import nao.events.ButtonLongPress;

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
    private Button RKnee_Left;

    @FXML
    private Button RKnee_Right;

    @FXML
    private Button LKnee_Up;

    @FXML
    private Button LKnee_Down;

    @FXML
    private Button LKnee_Left;

    @FXML
    private Button LKnee_Right;

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
			//TODO
		});
		
		new ButtonLongPress(LHip_Down, () -> {
			//TODO
		});
		
		new ButtonLongPress(LHip_Left, () -> {
			//TODO
		});
		
		new ButtonLongPress(LHip_Right, () -> {
			//TODO
		});
		
		new ButtonLongPress(RHip_Up, () -> {
			//TODO
		});
		
		new ButtonLongPress(RHip_Down, () -> {
			//TODO
		});
		
		new ButtonLongPress(RHip_Left, () -> {
			//TODO
		});
		
		new ButtonLongPress(RHip_Right, () -> {
			//TODO
		});
		
		new ButtonLongPress(RKnee_Up, () -> {
			//TODO
		});
		
		new ButtonLongPress(RKnee_Down, () -> {
			//TODO
		});
		
		new ButtonLongPress(RKnee_Left, () -> {
			//TODO
		});
		
		new ButtonLongPress(RKnee_Right, () -> {
			//TODO
		});
		
		new ButtonLongPress(LKnee_Up, () -> {
			//TODO
		});
		
		new ButtonLongPress(LKnee_Down, () -> {
			//TODO
		});
		
		new ButtonLongPress(LKnee_Left, () -> {
			//TODO
		});
		
		new ButtonLongPress(LKnee_Right, () -> {
			//TODO
		});
		
		new ButtonLongPress(LAnkle_Up, () -> {
			//TODO
		});
		
		new ButtonLongPress(LAnkle_Down, () -> {
			//TODO
		});
		
		new ButtonLongPress(LAnkle_Left, () -> {
			//TODO
		});
		
		new ButtonLongPress(LAnkle_Right, () -> {
			//TODO
		});
		
		new ButtonLongPress(RAnkle_Up, () -> {
			//TODO
		});
		
		new ButtonLongPress(RAnkle_Down, () -> {
			//TODO
		});
		
		new ButtonLongPress(RAnkle_Left, () -> {
			//TODO
		});
		
		new ButtonLongPress(RAnkle_Right, () -> {
			//TODO
		});
	}
}
