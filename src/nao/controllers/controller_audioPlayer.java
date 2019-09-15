package nao.controllers;
import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import nao.MainFrame;
import nao.SendWhile;
import nao.sender;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class controller_audioPlayer implements Initializable {
    public static controller_audioPlayer caP;
    private SendWhile s = new SendWhile();
    private File file;

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

        public void setTime(double time){
            int minutes = (int) time/60;
            int seconds = (int)time - (minutes*60);
            Length.setText( minutes + ":" + seconds);
        }

        public void setVolume(double volume) {
            this.volume.setValue(volume);
        }

        public void setPosition(double time){
            int minutes = (int) time/60;
            int seconds = (int)time - (minutes*60);
            this.time.setText(minutes + ":" + seconds);
        }

        @FXML
        void DeleteAllFiles(ActionEvent event) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "audioPlayer");
            jsonObject.add("function", "deleteFiles");
            sender.sendMessage(jsonObject.toJSONString());
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

        @FXML
        void fileSelect(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Hier du müssen Datei auswählen!");
            file = fileChooser.showOpenDialog(MainFrame.stage);

        }

        @FXML
        void fileUpload(ActionEvent event) {

        }

        @FXML
        void pause(ActionEvent event) {
            s.stopAudioPlayer();
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "audioPlayer");
            jsonObject.add("function", "pause");
            sender.sendMessage(jsonObject.toJSONString());
        }

        @FXML
        void play(ActionEvent event) {
            s.sendAudioPlayer();
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "audioPlayer");
            jsonObject.add("function", "play");
            sender.sendMessage(jsonObject.toJSONString());

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

        @FXML
        void unloadAllFiles(ActionEvent event) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "audioPlayer");
            jsonObject.add("function", "unloadFiles");
            sender.sendMessage(jsonObject.toJSONString());
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getVolume");
        sender.sendMessage(jsonObject.toJSONString());
    }
}

