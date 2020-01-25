package nao.controllers;
import components.json.JSONObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import nao.MainFrame;
import nao.SendMessages;
import nao.events.PromptButtonCell;
import nao.sender;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class AudioPlayer implements Initializable {
    public static AudioPlayer caP;
    private File file;
    private String fileNameForUpload;

    public AudioPlayer(){
        caP = this;
    }

    @FXML
    private TextField jumpSecond;

    @FXML
    private TextField jumpMinute;

    @FXML
    private Text positionTime;

    @FXML
    private Slider volume;

    @FXML
    private Text lengthOfFile;

    @FXML
    private ComboBox<String> fileLoadSelect;

    @FXML
    private Text fileName;

    @FXML
    private Button fileUploadSelectButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.add("type", "audioPlayer");
                jsonObject.add("function", "volume");
                jsonObject.add("masterVolume", t1);
                sender.sendMessage(jsonObject.toJSONString());
            }
        });
        fileName.setText("Filename");
        fileLoadSelect.setPromptText("Select a file ");
        fileLoadSelect.setButtonCell(new PromptButtonCell<>("Select a file"));
    }

    //---------------- FILES ------------------------------

    @FXML
    void fileUploadSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to upload!");
        file = fileChooser.showOpenDialog(MainFrame.stage);
        fileNameForUpload = file.getName();
        fileName.setText(fileNameForUpload);
    }

    @FXML
    void fileUpload(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[30000];
            int length = 0;
            while((length = fileInputStream.read(bytes)) != -1){
                byte[] base64 = Base64.getEncoder().encode(Arrays.copyOf(bytes, length));
                jsonObject.add("type", "audioPlayer");
                jsonObject.add("function", "file");
                jsonObject.add("name", fileNameForUpload);
                jsonObject.add("bytes", new String(base64, "UTF-8"));
                sender.sendMessage(jsonObject.toJSONString());
            }
            fileName.setText("Filename");
            fileUploadSelectButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            fileNameForUpload = null;
            file = null;
        } catch (Exception e) {
            fileUploadSelectButton.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
        SendMessages.sendAudioFiles();
        SendMessages.sendAllFiles();
    }

    @FXML
    void FileLoadButton(ActionEvent event) {
        if(fileLoadSelect.getSelectionModel().getSelectedItem() != null) {
            String name = fileLoadSelect.getSelectionModel().getSelectedItem();
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "audioPlayer");
            jsonObject.add("function", "setId");
            jsonObject.add("filename", name);
            sender.sendMessage(jsonObject.toJSONString());
            fileLoadSelect.getSelectionModel().clearSelection();
            fileLoadSelect.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else{
            fileLoadSelect.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    public void loadFiles(List<String> strings){
        fileLoadSelect.getItems().clear();
        fileLoadSelect.getItems().addAll(strings);
    }

    @FXML
    void reloadDirectory(ActionEvent event) {
        SendMessages.sendAudioFiles();
    }

    @FXML
    void unloadAllFiles(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "unloadFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void deleteAllFiles(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "deleteFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void fileDelete(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "deleteFile");
        jsonObject.add("fileDelete", fileLoadSelect.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

    //---------- VOLUME, PLAY, PAUSE ---------------------------

    public void setVolume(double volume) {
        this.volume.setValue(volume);
    }

    @FXML
    void pause(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "pause");
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.stopAudioPlayerPositionOfFile();
    }

    @FXML
    void play(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "play");
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.sendAudioPlayerPositionOfFile();
    }

    @FXML
    void playRepeat(ActionEvent event) {
        SendMessages.sendAudioPlayerPositionOfFile();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "playInLoop");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void stop(ActionEvent event) {
        SendMessages.stopAudioPlayerPositionOfFile();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());
    }


    //--------------- TIME ----------------------

    public void setPositionTime(double positionTime){
        int minutes = (int) positionTime /60;
        int seconds = (int) positionTime - (minutes*60);
        lengthOfFile.setText( minutes + ":" + seconds);
    }



    public void setPosition(double time){
        int minutes = (int) time/60;
        int seconds = (int)time - (minutes*60);
        this.positionTime.setText(minutes + ":" + seconds);
    }

    @FXML
    void jumpTo(ActionEvent event) {
        int minute = 0;
        int seconds = 0;
        int jumpSeconds = 0;
        try{
            minute = Integer.parseInt(jumpMinute.getText());
        }catch (NumberFormatException e) {}
        try{
            seconds = Integer.parseInt(jumpSecond.getText());
        }catch (NumberFormatException e) {}

        jumpSeconds = minute*60 + seconds;
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "jump");
        jsonObject.add("jumpToFloat", (float) jumpSeconds );
        sender.sendMessage(jsonObject.toJSONString());
    }



}

