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

/**
 * make Audio or Video records or take a photo
 */
public class Recorder {

    @FXML
    private ImageView audioRecorderImageView;

    @FXML
    private ImageView videoRecorderImageView;

    /**
     * Start an audio recording
     * @param event never used
     * @throws IOException throw an Exception
     */
    @FXML
    void audioRecorderPlay(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "startAudioRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        //change the image to an red point
        audioRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/RecordRed.png")),null));
    }

    /**
     * stop the audio recording
     * @param event never used
     * @throws IOException throw an Exception
     */
    @FXML
    void audioRecorderStop(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stopAudioRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        //reset the image into a black point
        audioRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/Record.png")),null));
    }

    /**
     * take a photo
     * @param event never used
     */
    @FXML
    void takePhoto(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "takePicture");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * start video recording
     * @param event never used
     * @throws IOException throw an Exception
     */
    @FXML
    void videoRecorderPlay(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "startVideoRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        //change the image to an red point
        videoRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/RecordRed.png")),null));
    }

    /**
     * stop video recording
     * @param event never used
     * @throws IOException throw an Exception
     */
    @FXML
    void videoRecorderStop(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Recorder");
        jsonObject.add("function", "stopVideoRecorder");
        sender.sendMessage(jsonObject.toJSONString());
        //reset the image into a black point
        videoRecorderImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("/icons/Record.png")),null));
    }
}
