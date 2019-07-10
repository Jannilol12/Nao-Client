package nao.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import nao.sender;

public class controller_walk {
	@FXML
    private TextField Steps;

    @FXML
    private CheckBox checkSteps;
    
    @FXML
    private TextField degree;
	
	@FXML
    void forward(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Forward\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Forward\", \"value\":\"0\"}");
        }
    }
	
	@FXML
    void backwards(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Backwards\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Backwards\", \"value\":\"0\"}");
        }
    }

    @FXML
    void left(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Left\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Left\", \"value\":\"0\"}");
        }
    }

    @FXML
    void right(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Right\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Right\", \"value\":\"0\"}");
        }
    }

    @FXML
    void stop(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Stop\", \"value\":\"0\"}");
    }

    @FXML
    void rotate(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Rotate\", \"value\":\"" + Integer.parseInt(degree.getText()) +"\"}");
    }
}
