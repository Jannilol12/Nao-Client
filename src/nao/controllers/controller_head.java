package nao.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import nao.sender;

public class controller_head {
	@FXML
    private Slider head_speed;
	
	@FXML
    void HeadPitch_Down(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadPitch_Down\", \"value\":" + head_speed.getValue() +"}");
    }

    @FXML
    void HeadPitch_Up(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadPitch_Up\", \"value\":" + head_speed.getValue() +"}");
    }

    @FXML
    void HeadYaw_Left(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadYaw_Left\", \"value\":" + head_speed.getValue() +"}");
    }

    @FXML
    void HeadYaw_Right(MouseEvent event) {
    	sender.sendMessage("{\"type\":\"motors\", \"motorname\":\"HeadYaw_Right\", \"value\":" + head_speed.getValue() +"}");
    }
}
