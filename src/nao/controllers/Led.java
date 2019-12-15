package nao.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;

public class Led implements Initializable {

    @FXML
    private TextField fade;

    @FXML
    private ColorPicker colour;

    @FXML
    private ComboBox<String> brain;

    @FXML
    private ComboBox<String> leftEar;

    @FXML
    private ComboBox<String> rightEar;

    @FXML
    private ComboBox<String> leftEye;

    @FXML
    private ComboBox<String> rightEye;

    @FXML
    private ComboBox<String> leftFoot;

    @FXML
    private ComboBox<String> rightFoot;

    @FXML
    private ComboBox<String> method;

    @FXML
    private TextField durationEyesRandom;

    @FXML
    private TextField rotationEyesRotate;

    @FXML
    private TextField durationEyesRotate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brain.getItems().add("Alle");
        for(int i = 0; i <= 11; i++) 
        	brain.getItems().add(i+"");

        leftEar.getItems().add("Alle");
        for(int i = 1; i <= 10; i++) 
        	leftEar.getItems().add(i+"");

        rightEar.getItems().add("Alle");
        for(int i = 1; i <= 10; i++) 
        	rightEar.getItems().add(i+"");

        leftEye.getItems().add("Alle");
        for(int i = 1; i <= 8; i++) 
        	rightEar.getItems().add(i+"");

        rightEye.getItems().add("Alle");
        for(int i = 1; i <= 8; i++) 
        	rightEar.getItems().add(i+"");

        leftFoot.getItems().addAll("Alle", "Blue", "Green", "Red");
        rightFoot.getItems().addAll("Alle", "Blue", "Green", "Red");
        method.getItems().addAll("rgb", "on", "off");
    }

    @FXML
    void startButton(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.add("type", "Leds");
    	
        try {
            if(!durationEyesRandom.getText().isEmpty()){
            	jsonObject.add("method", "random");
            	jsonObject.add("duration", Float.parseFloat(durationEyesRandom.getText()));
            	
            	sender.sendMessage(jsonObject.toJSONString());
            	
            } else if(!durationEyesRotate.getText().isEmpty() && !rotationEyesRotate.getText().isEmpty()){
                int rgb = (int) colour.getValue().getRed() << 8*2 + (int) colour.getValue().getGreen() << 8 + (int) colour.getValue().getBlue();
                jsonObject.add("method", "rotate");
                jsonObject.add("rgbe", rgb);
                jsonObject.add("timeR", Float.parseFloat(rotationEyesRotate.getText()));
                jsonObject.add("timeD", Float.parseFloat(rotationEyesRotate.getText()));
                
                sender.sendMessage(jsonObject.toJSONString());
                
            } else {
            	jsonObject.add("method", method.getSelectionModel().getSelectedItem());
            	jsonObject.add("red", (float) colour.getValue().getRed());
            	jsonObject.add("green", colour.getValue().getGreen());
            	jsonObject.add("blue", colour.getValue().getBlue());
            	jsonObject.add("Fade", Float.parseFloat(fade.getText()));
            	
                if (brain.getSelectionModel().getSelectedItem() != null) {
                    if (brain.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 0; i < 12; i++) {
                        	jsonObject.add("ledname", "Brain" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "Brain" + brain.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (leftEar.getSelectionModel().getSelectedItem() != null) {
                    if (leftEar.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 11; i++) {
                        	jsonObject.add("ledname", "LeftEarLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "LeftEarLed" + leftEar.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (rightEar.getSelectionModel().getSelectedItem() != null) {
                    if (rightEar.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 11; i++) {
                        	jsonObject.add("ledname", "RightEarLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "RightEarLed" + rightEar.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (leftEye.getSelectionModel().getSelectedItem() != null) {
                    if (leftEye.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                        	jsonObject.add("ledname", "LeftFaceLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "LeftFaceLed" + leftEye.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (rightEye.getSelectionModel().getSelectedItem() != null) {
                    if (rightEye.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                        	jsonObject.add("ledname", "RightFaceLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "RightFaceLed" + rightEye.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (leftFoot.getSelectionModel().getSelectedItem() != null) {
                    if (leftFoot.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                    	jsonObject.add("ledname", "LeftFootLedsBlue");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "LeftFootLedsGreen");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "LeftFootLedsRed");
                    	sender.sendMessage(jsonObject.toJSONString());
                    } else {
                    	jsonObject.add("ledname", "LeftFootLeds" + leftFoot.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (rightFoot.getSelectionModel().getSelectedItem() != null) {
                    if (rightFoot.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                    	jsonObject.add("ledname", "RightFootLedsBlue");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "RightFootLedsGreen");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "RightFootLedsRed");
                    	sender.sendMessage(jsonObject.toJSONString());
                    } else {
                    	jsonObject.add("ledname", "RightFootLeds" + rightFoot.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
            }
        }catch (NumberFormatException err){
            System.out.println("NumberFormatException");
        }
        brain.getSelectionModel().clearSelection();
        brain.setPromptText("Brain");
        rightEar.setPromptText("Right ear");
        leftEar.setPromptText("Left ear");
        rightEye.setPromptText("Right Eye");
        leftEye.setPromptText("Left Eye");
        rightFoot.setPromptText("Right foot");
        leftFoot.setPromptText("Left foot");
    }

}
