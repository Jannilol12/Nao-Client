package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import nao.SendMessages;
import nao.events.PromptButtonCell;
import nao.sender;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SpeechFaceBehavior implements Initializable {
    private boolean isSpeechRecognitionButtonSelected = false;
    private boolean isFaceDetectionSelected = false;
    private boolean isFaceTrackingSelected = false;
    public static SpeechFaceBehavior cE;

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

    public SpeechFaceBehavior(){
        cE = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        speechVocList.setPromptText("Select vocable");
        speechVocList.setButtonCell(new PromptButtonCell<>("Select vocable"));
        faceBox.setPromptText("Select face");
        faceBox.setButtonCell(new PromptButtonCell<>("Select face"));
        behaviorFileSelector.setPromptText("Select behavior");
        behaviorFileSelector.setButtonCell(new PromptButtonCell<>("Select behavior"));
    }

    public void setVocabulary(List<String> list){
        speechVocList.getItems().addAll(list);
    }

    public void setNames(List<String> list){
        faceBox.getItems().clear();
        faceBox.getItems().addAll(list);
    }

    @FXML
    void faceAdd(ActionEvent event) {
        if(faceName.getText().compareToIgnoreCase("") > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "LearnFace");
            jsonObject.add("String", faceName.getText());
            sender.sendMessage(jsonObject.toJSONString());
            SendMessages.sendFaceNames();
            faceName.setText("");
            faceName.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            faceName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    @FXML
    void faceDelete(ActionEvent event) {
        if(faceBox.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "DeleteFace");
            jsonObject.add("Face", faceBox.getSelectionModel().getSelectedItem());
            sender.sendMessage(jsonObject.toJSONString());
            SendMessages.sendFaceNames();
            faceBox.getSelectionModel().clearSelection();
            faceName.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            faceBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
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
        if(speechVocText.getText().compareToIgnoreCase("") > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "AddVocabulary");
            jsonObject.add("String", speechVocText.getText());
            sender.sendMessage(jsonObject.toJSONString());
            speechVocText.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            speechVocText.setText("");
        } else{
            speechVocText.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));

        }
    }

    @FXML
    void speechDeleteVoc(ActionEvent event) {
        if(speechVocList.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "DeleteVocabulary");
            jsonObject.add("String", speechVocList.getSelectionModel().getSelectedItem());
            sender.sendMessage(jsonObject.toJSONString());
            speechVocList.getSelectionModel().clearSelection();
            speechVocList.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            speechVocList.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
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
        if(behaviorFileSelector.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Behavior");
            jsonObject.add("function", "play");
            jsonObject.add("name", behaviorFileSelector.getSelectionModel().getSelectedItem());
            sender.sendMessage(jsonObject.toJSONString());
            behaviorFileSelector.getSelectionModel().clearSelection();
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    @FXML
    void behaviorReload(ActionEvent event) {
        SendMessages.sendBehavior();
        behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
    }

    @FXML
    void behaviorRemove(ActionEvent event) {
        if(behaviorFileSelector.getSelectionModel().getSelectedItem() != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Behavior");
            jsonObject.add("function", "removeBehavior");
            jsonObject.add("behaviorname", behaviorFileSelector.getSelectionModel().getSelectedItem() );
            sender.sendMessage(jsonObject.toJSONString());
            SendMessages.sendBehavior();
            behaviorFileSelector.getSelectionModel().clearSelection();
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    @FXML
    void behaviorStop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());
        behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
    }

}
