package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nao.sender;

public class Recorder {

    @FXML
    private TextField recorderFileName;

    @FXML
    void audioRecorderPlay(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "startAudioRecorder");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void audioRecorderStop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stopAudioRecorder");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void takePhoto(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "takePicture");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void videoRecorderPlay(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "startVideoRecorder");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void videoRecorderStop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stopVideoRecorder");
        sender.sendMessage(jsonObject.toJSONString());
    }
}
