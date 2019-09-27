package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import nao.sender;


public class controller_Events {
    private boolean isFeetContactButtonSelected = false;
    private boolean isSpeechRecognitionButtonSelected = false;
    private boolean isSonarButtonSelected = false;

    @FXML
    private ToggleButton FeetContactButton;

    @FXML
    private ToggleButton SpeechRecognitionButton;

    @FXML
    private ToggleButton SonarButton;

    @FXML
    void FeetContact(ActionEvent event) {
        if(isFeetContactButtonSelected){
            FeetContactButton.setStyle("-fx-background-color: Red");
            isFeetContactButtonSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FootContact");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            FeetContactButton.setStyle("-fx-background-color: Green");
            isFeetContactButtonSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FootContact");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void SpeechRecognition(ActionEvent event) {
        if(isSpeechRecognitionButtonSelected){
            SpeechRecognitionButton.setStyle("-fx-background-color: Red");
            isSpeechRecognitionButtonSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "SpeechRecognition");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            SpeechRecognitionButton.setStyle("-fx-background-color: Green");
            isSpeechRecognitionButtonSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "SpeechRecognition");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void Sonar(ActionEvent event) {
        if(isSonarButtonSelected){
            SonarButton.setStyle("-fx-background-color: Red");
            isSonarButtonSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Sonar");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            SonarButton.setStyle("-fx-background-color: Green");
            isSonarButtonSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Sonar");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }
}
