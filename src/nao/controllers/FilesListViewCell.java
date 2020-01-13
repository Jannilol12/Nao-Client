package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

public class FilesListViewCell implements Initializable {
    String fileName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfFile.setText(fileName);
    }

    public FilesListViewCell(String fileName){
        this.fileName = fileName;
    }

    @FXML
    private Text nameOfFile;

    @FXML
    void fileDelete(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Files");
        jsonObject.add("function", "deleteFile");
        jsonObject.add("filename",  fileName);
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void fileDownload(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Files");
        jsonObject.add("function", "downloadFile");
        jsonObject.add("filename",  fileName);
        sender.sendMessage(jsonObject.toJSONString());
    }

}
