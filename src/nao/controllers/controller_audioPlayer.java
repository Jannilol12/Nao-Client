package nao.controllers;
import components.json.JSONObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import nao.MainFrame;
import nao.SendMessages;
import nao.sender;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class controller_audioPlayer implements Initializable {
    public static controller_audioPlayer caP;
    private SendMessages s = new SendMessages();
    private File file;
    private String fileName;

    public controller_audioPlayer(){
        caP = this;
    }


    @FXML
    private TextField jumpSecond;

    @FXML
    private TextField jumpMinute;

    @FXML
    private Text time;

    @FXML
    private Slider volume;

    @FXML
    private Text Length;

    @FXML
    private ComboBox<String> FileSelector;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getVolume");
        sender.sendMessage(jsonObject.toJSONString());

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
    }

    //---------------- FILES ------------------------------

    @FXML
    void fileSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Hier du müssen Datei auswählen!");
        file = fileChooser.showOpenDialog(MainFrame.stage);
        fileName = file.getName();
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
                jsonObject.add("name", fileName);
                jsonObject.add("bytes", new String(base64, "UTF-8"));
                sender.sendMessage(jsonObject.toJSONString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        s.sendFileRequest();
    }

    @FXML
    void FileLoadButton(ActionEvent event) {
        String name = FileSelector.getSelectionModel().getSelectedItem();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "setId");
        jsonObject.add("filename", name );
        System.out.println(name);
        sender.sendMessage(jsonObject.toJSONString());
    }

    public void loadFiles(List<String> strings){
        FileSelector.getItems().addAll(strings);
    }

    @FXML
    void unloadAllFiles(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "unloadFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void DeleteAllFiles(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "deleteFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void DeleteFile(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "deleteFile");
        jsonObject.add("fileDelete", FileSelector.getSelectionModel().getSelectedItem());
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
        s.stopAudioPlayer();
    }

    @FXML
    void play(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "play");
        sender.sendMessage(jsonObject.toJSONString());
        s.sendAudioPlayer();
    }

    @FXML
    void playRepeat(ActionEvent event) {
        s.sendAudioPlayer();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "playInLoop");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void stop(ActionEvent event) {
        s.stopAudioPlayer();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());
    }


    //--------------- TIME ----------------------

    public void setTime(double time){
        int minutes = (int) time/60;
        int seconds = (int)time - (minutes*60);
        Length.setText( minutes + ":" + seconds);
    }



    public void setPosition(double time){
        int minutes = (int) time/60;
        int seconds = (int)time - (minutes*60);
        this.time.setText(minutes + ":" + seconds);
    }

    @FXML
    void Jump(ActionEvent event) {
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

