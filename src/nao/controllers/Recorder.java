package nao.controllers;

import components.json.JSONObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nao.sender;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Recorder {

    @FXML
    private ImageView audioRecorderImageView;

    @FXML
    private ImageView videoRecorderImageView;

    @FXML
    private TextField recorderFileName;

    @FXML
    void audioRecorderPlay(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "startAudioRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        audioRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/RecordRed.png")),null));
    }

    @FXML
    void audioRecorderStop(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stopAudioRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        audioRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/Record.png")),null));
    }

    @FXML
    void takePhoto(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "takePicture");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void videoRecorderPlay(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "startVideoRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        videoRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/RecordRed.png")),null));
    }

    @FXML
    void videoRecorderStop(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stopVideoRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        videoRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/Record.png")),null));
    }
}
