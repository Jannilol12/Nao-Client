package nao.controllers;

import components.json.JSONObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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

public class controller_behavior {

    public static controller_behavior cB;
    private File file;
    private String fileName;

    public controller_behavior(){
        cB = this;
    }


    @FXML
    private ComboBox<String> FileSelector;

    public void loadFiles(List<String> strings){
        FileSelector.getItems().addAll(strings);
    }

    @FXML
    void FileLoadButton(ActionEvent event) {
        String name = FileSelector.getSelectionModel().getSelectedItem();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "setId");
        jsonObject.add("behaviorname", name );
        System.out.println(name);
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void Play(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "play");
        jsonObject.add("name", FileSelector.getSelectionModel().getSelectedItem());
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void RemoveFileButton(ActionEvent event) {
        String name = FileSelector.getSelectionModel().getSelectedItem();
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "removeBehavior");
        jsonObject.add("behaviorname", name );
        System.out.println(name);
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void Stop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "stop");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void fileSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wer das liest ist doof!");
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
                jsonObject.add("type", "Behavior");
                jsonObject.add("function", "file");
                jsonObject.add("Bname", fileName);
                jsonObject.add("bytes", new String(base64, "UTF-8"));
                sender.sendMessage(jsonObject.toJSONString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SendMessages.sendBehavior();
    }
}
