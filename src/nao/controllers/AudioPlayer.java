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

/**
 * Control the AudioPlayer and play Audio Files
 */
public class AudioPlayer implements Initializable {
    public static AudioPlayer caP;
    private File file;
    private String fileNameForUpload;

    /**
     * This is used for the MainReceiver, so other classes can use methods
     */
    public AudioPlayer() {
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

    /**
     * Initialize is like a constructor for JavaFX
     * @param url            never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //listener for the volume, so if it is changed send a message with the new volume to the robot
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

        //because the prompt text from a ComboBox is deleted after selected something,
        // and if you clear the selection the text also won't come back,
        // I copied a Listener from StackOverFlow
        fileLoadSelect.setPromptText("Select a file ");
        fileLoadSelect.setButtonCell(new PromptButtonCell<>("Select a file"));
    }

    //---------------- FILES ------------------------------

    /**
     * Select a file, to upload it...see {@link #fileUpload(ActionEvent)}
     * @param event never used
     */
    @FXML
    void fileUploadSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to upload!");
        file = fileChooser.showOpenDialog(MainFrame.stage);
        fileNameForUpload = file.getName();
        fileName.setText(fileNameForUpload);
    }

    /**
     * Upload a file
     * @param event never used
     */
    @FXML
    void fileUpload(ActionEvent event) {
        /*
			how this works:
			The File is decoded in Base64 and is split into many Messages
			Every time a message is received the file will be written until it's finished
			So the file will be written step by step
		*/
        JSONObject jsonObject = new JSONObject();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[30000];
            int length = 0;
            //take the first 30000 bytes of the file and send them as a String,
            //if there are more send another 30000 and so on and so on until there is nothing left
            while ((length = fileInputStream.read(bytes)) != -1) {
                //encode the file as Base64
                byte[] base64 = Base64.getEncoder().encode(Arrays.copyOf(bytes, length));
                jsonObject.add("type", "audioPlayer");
                jsonObject.add("function", "file");
                jsonObject.add("name", fileNameForUpload); //send the name
                jsonObject.add("bytes", new String(base64, "UTF-8")); //send the base64 as a String
                sender.sendMessage(jsonObject.toJSONString());
            }
            //reset everything
            fileName.setText("Filename");
            fileUploadSelectButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            fileNameForUpload = null;
            file = null;
        } catch (Exception e) {
            //if no file selected set the Border of the select Button RED!
            fileUploadSelectButton.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
        SendMessages.sendAudioFiles(); //refresh the List with the Audio Files at AudioPlayer
        SendMessages.sendAllFiles(); //refresh the List in the Files Tab
    }

    /**
     * Load a file, to play it
     * @param event never used
     */
    @FXML
    void FileLoadButton(ActionEvent event) {
        //if nothing is selected set the Border from the ComboBox RED
        if (fileLoadSelect.getSelectionModel().getSelectedItem() != null) {
            //send the name of the file, which shell be loaded
            String name = fileLoadSelect.getSelectionModel().getSelectedItem();
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "audioPlayer");
            jsonObject.add("function", "setId");
            jsonObject.add("filename", name);
            sender.sendMessage(jsonObject.toJSONString());
            //reset the selection and the Border
            fileLoadSelect.getSelectionModel().clearSelection();
            fileLoadSelect.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } else {
            fileLoadSelect.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * load the ComboBox with AudioFiles
     * @param strings List with all filenames
     */
    public void loadFiles(List<String> strings) {
        fileLoadSelect.getItems().clear(); //clear(), not removeAll()!
        fileLoadSelect.getItems().addAll(strings);
    }

    /**
     * Send the request for reloading the AudioFiles
     * @param event never used
     */
    @FXML
    void reloadDirectory(ActionEvent event) {
        SendMessages.sendAudioFiles();
    }

    /**
     * unload every file which is loaded
     * @param event never used
     */
    @FXML
    void unloadAllFiles(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "unloadFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    //---------- VOLUME, PLAY, PAUSE ---------------------------

    /**
     * set the volume slider
     * @param volume between 0 and 1
     */
    public void setVolume(double volume) {
        this.volume.setValue(volume);
    }

    /**
     * pause the audio file which is played
     * @param event never used
     */
    @FXML
    void pause(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "pause");
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.stopAudioPlayerPositionOfFile();
    }

    /**
     * play an audio file
     * @param event never used
     */
    @FXML
    void play(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "play");
        sender.sendMessage(jsonObject.toJSONString());
        SendMessages.sendAudioPlayerPositionOfFile(); //get the position where the file which is playing is
    }

    /**
     * play an audio file in a loop
     * @param event never used
     */
    @FXML
    void playRepeat(ActionEvent event) {
        SendMessages.sendAudioPlayerPositionOfFile(); //get the position where the file which is playing is
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

    /**
     * set the length of the file!
     * @param positionTime get the time as a double
     */
    public void setPositionTime(double positionTime) {
        int minutes = (int) positionTime / 60;
        int seconds = (int) positionTime - (minutes * 60);
        lengthOfFile.setText(minutes + ":" + seconds);
    }

    /**
     * set the time where the file which is played is
     * @param time get the time as a double
     */
    public void setPosition(double time) {
        int minutes = (int) time / 60;
        int seconds = (int) time - (minutes * 60);
        this.positionTime.setText(minutes + ":" + seconds);
    }

    /**
     * jump to minute/second
     * @param event never used
     */
    @FXML
    void jumpTo(ActionEvent event) {
        int minute = 0;
        int seconds = 0;
        int jumpSeconds;
        try {
            minute = Integer.parseInt(jumpMinute.getText());
        } catch (NumberFormatException e) {
        }
        try {
            seconds = Integer.parseInt(jumpSecond.getText());
        } catch (NumberFormatException e) {
        }

        jumpSeconds = minute * 60 + seconds;
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "jump");
        jsonObject.add("jumpToFloat", (float) jumpSeconds);
        sender.sendMessage(jsonObject.toJSONString());
    }
}

