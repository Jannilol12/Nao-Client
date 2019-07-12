package nao.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

public class controller_walk implements Initializable {
	@FXML
    private TextField Steps;

    @FXML
    private CheckBox checkSteps;
    
    @FXML
    private TextField degree;

    @FXML
    private ComboBox<String> PostureBox;

    @FXML
    private TextField PostureSpeed;

    @FXML
    void Wake_Up(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Wakeup\"}");
    }
	
	@FXML
    void forward(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Forward\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"}");
        }
        else {
            sender.sendMessage("{\"type\":\"Forward\", \"value\":\"0\"}");
        }
    }
	
	@FXML
    void backwards(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Backwards\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"}");
        }
        else {
            sender.sendMessage("{\"type\":\"Backwards\", \"value\":\"0\"}");
        }
    }

    @FXML
    void left(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Left\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"}");
        }
        else {
            sender.sendMessage("{\"type\":\"Left\", \"value\":\"0\"}");
        }
    }

    @FXML
    void right(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Right\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"}");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PostureBox.getItems().add("Stand");
        PostureBox.getItems().add("StandInit");
        PostureBox.getItems().add("StandZero");
        PostureBox.getItems().add("Crouch");
        PostureBox.getItems().add("Sit");
        PostureBox.getItems().add("SitRelax");
        PostureBox.getItems().add("LyingBelly");
        PostureBox.getItems().add("LyingBack");
    }

    @FXML
    void PostureStart(ActionEvent event) {
        sender.sendMessage("{\"type\":\"posture\", \"position\": \"" + PostureBox.getSelectionModel().getSelectedItem() + "\", \"speed\":" + Float.parseFloat(PostureSpeed.getText()) + "}");
    }
}
