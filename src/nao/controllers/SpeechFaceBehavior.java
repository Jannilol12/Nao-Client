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

/**
 * Control the Speech Recognition, the Face Detection and the Behavior
 */
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

    /**
     * This is used for the MainReceiver,
     */
    public SpeechFaceBehavior(){
        cE = this;
    }

    /**
     * Initialize is like a constructor for JavaFX
     * @param url            never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //because the prompt text from a ComboBox is deleted after selected something,
        // and if you clear the selection the text also won't come back,
        // I copied a Listener from StackOverFlow
        speechVocList.setPromptText("Select vocable");
        speechVocList.setButtonCell(new PromptButtonCell<>("Select vocable"));

        faceBox.setPromptText("Select face");
        faceBox.setButtonCell(new PromptButtonCell<>("Select face"));

        behaviorFileSelector.setPromptText("Select behavior");
        behaviorFileSelector.setButtonCell(new PromptButtonCell<>("Select behavior"));
    }

    /**
     * add the names of the learned faces into the ComboBox
     * @param list list of names
     */
    public void setNames(List<String> list){
        faceBox.getItems().clear();
        faceBox.getItems().addAll(list);
    }

    /**
     * Reload all learned faces -> get the names
     * @param event never used
     */
    @FXML
    void faceReload(ActionEvent event) {
        SendMessages.sendFaceNames();
    }

    /**
     * learn a new face
     * @param event nver used
     */
    @FXML
    void faceAdd(ActionEvent event) {
        //if no name is typed in into the TextField set the Border of it RED
        if(faceName.getText().compareToIgnoreCase("") > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "LearnFace");
            jsonObject.add("String", faceName.getText());
            sender.sendMessage(jsonObject.toJSONString());

            //reload the faces
            SendMessages.sendFaceNames();

            //reset the Border and the TextField
            faceName.setText("");
            faceName.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            faceName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * delete a face from the system of the robot
     * @param event never used
     */
    @FXML
    void faceDelete(ActionEvent event) {
        //if no face is selected set the Border of the ComboBox RED
        if(faceBox.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "DeleteFace");
            jsonObject.add("Face", faceBox.getSelectionModel().getSelectedItem());
            sender.sendMessage(jsonObject.toJSONString());

            //reload the faces
            SendMessages.sendFaceNames();

            //reset the Border and the ComboBox
            faceBox.getSelectionModel().clearSelection();
            faceName.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            faceBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * start/stop the face detection
     * @param event never used
     */
    @FXML
    void faceDetection(ActionEvent event) {
        //if -> stop, else -> start
        if(isFaceDetectionSelected){
            //set the colour of the Button RED and set the boolean false (faceDetection is stopped)
            faceDetectionButton.setStyle("-fx-background-color: Red");
            isFaceDetectionSelected = false;

            //send STOP
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceDetection");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            //set the colour of the Button GREEN and set the boolean true (faceDetection is stopped)
            faceDetectionButton.setStyle("-fx-background-color: Green");
            isFaceDetectionSelected = true;

            //send START
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceDetection");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * start/stop the face tracking
     * @param event never used
     */
    @FXML
    void faceTracking(ActionEvent event) {
        //if -> stop, else -> start
        if(isFaceTrackingSelected){
            //set the colour of the Button RED and set the boolean false (faceTracking is stopped)
            faceTrackingButton.setStyle("-fx-background-color: Red");
            isFaceTrackingSelected = false;

            //send STOP
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceTracking");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            //set the colour of the Button GREEN and set the boolean true (faceTracking is stopped)
            faceTrackingButton.setStyle("-fx-background-color: Green");
            isFaceTrackingSelected = true;

            //send START
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FaceTracking");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * add the vocabulary from the robot into the ComboBox
     * @param list list of names
     */
    public void setVocabulary(List<String> list){
        speechVocList.getItems().addAll(list);
    }

    /**
     * reload the vocabulary -> get the words
     * @param event never used
     */
    @FXML
    void vocabularReload(ActionEvent event) {
        SendMessages.sendVocabulary();
    }

    /**
     * add a vocable
     * @param event never used
     */
    @FXML
    void speechAddVoc(ActionEvent event) {
        //if no Text is in the TextField, change the border of the Field to RED
        if(speechVocText.getText().compareToIgnoreCase("") > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "AddVocabulary");
            jsonObject.add("String", speechVocText.getText());

            //reload the vocabulary
            sender.sendMessage(jsonObject.toJSONString());

            //reset the Border and the TextField
            speechVocText.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            speechVocText.setText("");
        } else{
            speechVocText.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));

        }
    }

    /**
     * delete a vocable
     * @param event never used
     */
    @FXML
    void speechDeleteVoc(ActionEvent event) {
        //if no vocable is selected, change the border of the ComboBox RED
        if(speechVocList.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "DeleteVocabulary");
            jsonObject.add("String", speechVocList.getSelectionModel().getSelectedItem());

            //reload the vocabulary
            sender.sendMessage(jsonObject.toJSONString());

            //reset the border and the TextField
            speechVocList.getSelectionModel().clearSelection();
            speechVocList.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            speechVocList.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * start/stop the speech recognition
     * @param event
     */
    @FXML
    void speechRecognition(ActionEvent event) {
        //if -> stop, else -> start
        if(isSpeechRecognitionButtonSelected){
            //set the colour of the Button RED and set the boolean false (speechRecognition is stopped)
            speechRecognitionButton.setStyle("-fx-background-color: Red");
            isSpeechRecognitionButtonSelected = false;

            //send STOP
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "SpeechRecognition");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            //set the colour of the Button GREEN and set the boolean true (speechRecognition is stopped)
            speechRecognitionButton.setStyle("-fx-background-color: Green");
            isSpeechRecognitionButtonSelected = true;

            //send START
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "SpeechRecognition");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * add given behaviors to the ComboBox
     * @param strings all behaviors which are installed on the robot
     */
    public void loadBehaviors(List<String> strings){
        behaviorFileSelector.getItems().clear();
        behaviorFileSelector.getItems().addAll(strings);
    }

    /**
     * play a behavior
     * @param event never used
     */
    @FXML
    void behaviorPlay(ActionEvent event) {
        //if no behavior is selected change the border of the ComboBox to RED
        if(behaviorFileSelector.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Behavior");
            jsonObject.add("function", "play");
            jsonObject.add("name", behaviorFileSelector.getSelectionModel().getSelectedItem());

            //reload the behaviors
            sender.sendMessage(jsonObject.toJSONString());

            //reset the border and the selection of the ComboBox
            behaviorFileSelector.getSelectionModel().clearSelection();
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * Reload the behavior
     * @param event never used
     */
    @FXML
    void behaviorReload(ActionEvent event) {
        SendMessages.sendBehavior();
        behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
    }

    /**
     * uninstalling a behavior
     * @param event never used
     */
    @FXML
    void behaviorRemove(ActionEvent event) {
        //if no behavior is selected, change the border of the ComboBox RED
        if(behaviorFileSelector.getSelectionModel().getSelectedItem() != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Behavior");
            jsonObject.add("function", "removeBehavior");
            jsonObject.add("behaviorname", behaviorFileSelector.getSelectionModel().getSelectedItem() );
            sender.sendMessage(jsonObject.toJSONString());

            //reload the behaviors
            SendMessages.sendBehavior();

            //reset the border and the selection
            behaviorFileSelector.getSelectionModel().clearSelection();
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * stop the behavior
     * @param event never used
     */
    @FXML
    void behaviorStop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());

        behaviorFileSelector.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
    }

}
