package nao.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import nao.sender;

public class controller_arm {
	@FXML
    private Slider arms_speed;
	
	@FXML
    void LShoulderPitch_Down(MouseEvent event) {
        sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderPitch_Down\", \"value\":" + arms_speed.getValue() +"}");
    }

    @FXML
    void LShoulderPitch_Up(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderPitch_Up\", \"value\":" + arms_speed.getValue() +"}");
    }

    @FXML
    void LShoulderRoll_Left(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderRoll_Left\", \"value\":" + arms_speed.getValue() +"}");
    }

    @FXML
    void LShoulderRoll_Right(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"LShoulderRoll_Right\", \"value\":" + arms_speed.getValue() +"}");
    }
    
    @FXML
    void RShoulderPitch_Down(MouseEvent event) {
        sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderPitch_Down\", \"value\":" + arms_speed.getValue() +"}");
    }

    @FXML
    void RShoulderPitch_Up(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderPitch_Up\", \"value\":" + arms_speed.getValue() +"}");
    }

    @FXML
    void RShoulderRoll_Left(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderRoll_Left\", \"value\":" + arms_speed.getValue() +"}");
    }

    @FXML
    void RShoulderRoll_Right(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"RShoulderRoll_Right\", \"value\":" + arms_speed.getValue() +"}");
    }
}
