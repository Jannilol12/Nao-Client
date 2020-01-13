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


public class SpeechFaceBehavior {
    private boolean isSpeechRecognitionButtonSelected = false;
    private boolean isFaceDetectionSelected = false;
    private boolean isFaceTrackingSelected = false;
    public static SpeechFaceBehavior cE;

    public SpeechFaceBehavior(){
        cE = this;
    }

    public void setVocabulary(List<String> list){
        speechVocList.getItems().addAll(list);
    }

    public void setNames(List<String> list){
        faceBox.getItems().clear();
        faceBox.getItems().addAll(list);
    }

    @FXML
    private TextField speechVocText;

    @FXML
    private ComboBox<String> speechVocList;

    @FXML
    private ToggleButton speechRecognitionButton;

    @FXML
    private ToggleButton faceDetectionButton;

    @FXML
    private ToggleButton faceTrackingButton;

    @FXML
    private TextField faceName;

    @FXML
    private ComboBox<String> faceBox;

    @FXML
    private ComboBox<String> behaviorFileSelector;

    @FXML
    void faceAdd(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "LearnFace");
        jsonObject.add("String",  faceName.getText());
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.sendFaceNames();
    }

    @FXML
    void faceDelete(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "DeleteFace");
        jsonObject.add("Face",  faceBox.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.sendFaceNames();
    }

    @FXML
    void faceDetection(ActionEvent event) {
        if(isFaceDetectionSelected){
            faceDetectionButton.setStyle("-fx-background-color: Red");
            isFaceDetectionSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceDetection");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            faceDetectionButton.setStyle("-fx-background-color: Green");
            isFaceDetectionSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceDetection");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void faceTracking(ActionEvent event) {
        if(isFaceTrackingSelected){
            faceTrackingButton.setStyle("-fx-background-color: Red");
            isFaceTrackingSelected = false;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceTracking");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            faceTrackingButton.setStyle("-fx-background-color: Green");
            isFaceTrackingSelected = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceTracking");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void speechAddVoc(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "AddVocabulary");
        jsonObject.add("String",  speechVocText.getText());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void speechDeleteVoc(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "DeleteVocabulary");
        jsonObject.add("String",  speechVocList.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }



    @FXML
    void speechRecognition(ActionEvent event) {
        if(isSpeechRecognitionButtonSelected){
            speechRecognitionButton.setStyle("-fx-background-color: Red");
            isSpeechRecognitionButtonSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "SpeechRecognition");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            speechRecognitionButton.setStyle("-fx-background-color: Green");
            isSpeechRecognitionButtonSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "SpeechRecognition");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    public void loadBehaviors(List<String> strings){
        behaviorFileSelector.getItems().clear();
        behaviorFileSelector.getItems().addAll(strings);
    }

    @FXML
    void behaviorPlay(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "play");
        jsonObject.add("name", behaviorFileSelector.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void behaviorReload(ActionEvent event) {
        SendMessages.sendBehavior();
    }

    @FXML
    void behaviorRemove(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "removeBehavior");
        jsonObject.add("behaviorname", behaviorFileSelector.getSelectionModel().getSelectedItem() );
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.sendBehavior();
    }

    @FXML
    void behaviorStop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());
    }


}
