package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nao.sender;

public class AudioRecorder {

    @FXML
    private TextField recorderFileName;

    @FXML
    void recorderPlay(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "start");
        jsonObject.add("filename",  recorderFileName.getText());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void recorderStop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());
    }
}
