package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import nao.SendMessages;
import nao.sender;

import java.util.List;


public class controller_Events {
    private boolean isFeetContactButtonSelected = false;
    private boolean isSpeechRecognitionButtonSelected = false;
    private boolean isSonarButtonSelected = false;
    private boolean isFaceDetectionSelected = false;
    private boolean isFaceTrackingSelected = false;
    public static controller_Events cE;

    public controller_Events(){
        cE = this;
    }

    public void setVocabulary(List<String> list){
        VocList.getItems().addAll(list);
    }

    public void setNames(List<String> list){
        FaceBox.getItems().addAll(list);
    }

    @FXML
    private TextField VocText;

    @FXML
    private ComboBox<String> VocList;

    @FXML
    private ToggleButton FaceDetectionButton;

    @FXML
    private ToggleButton FaceTrackingButton;

    @FXML
    private ToggleButton FeetContactButton;

    @FXML
    private ToggleButton SpeechRecognitionButton;

    @FXML
    private ToggleButton SonarButton;

    @FXML
    private TextField FaceName;

    @FXML
    private ComboBox<String> FaceBox;

    @FXML
    void FaceAdd(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "LearnFace");
        jsonObject.add("String",  FaceName.getText());
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.sendFaces();
    }

    @FXML
    void FaceDelete(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "DeleteFace");
        jsonObject.add("Face",  FaceBox.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void FaceDetection(ActionEvent event) {
        if(isFaceDetectionSelected){
            FaceDetectionButton.setStyle("-fx-background-color: Red");
            isFaceDetectionSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceDetection");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            FaceDetectionButton.setStyle("-fx-background-color: Green");
            isFaceDetectionSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceDetection");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void FaceTracking(ActionEvent event) {
        if(isFaceTrackingSelected){
            FaceTrackingButton.setStyle("-fx-background-color: Red");
            isFaceTrackingSelected = false;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceTracking");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            FaceTrackingButton.setStyle("-fx-background-color: Green");
            isFaceTrackingSelected = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceTracking");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void AddVoc(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "AddVocabulary");
        jsonObject.add("String",  VocText.getText());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void DeleteVoc(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "DeleteVocabulary");
        jsonObject.add("String",  VocList.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

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
