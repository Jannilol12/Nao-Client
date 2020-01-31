package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Item from the Files list
 */
public class FilesListViewCell implements Initializable {
    String fileName;

    @FXML
    private Text nameOfFile;

    /**
     * Initialize is like a constructor for JavaFX
     * @param url            never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfFile.setText(fileName);
    }

    /**
     * @param fileName get the name of the file which this item represents
     * Set the name of the file
     */
    public FilesListViewCell(String fileName){
        this.fileName = fileName;
    }

    /**
     * Delete this file from the robot
     * @param event never used
     */
    @FXML
    void fileDelete(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Files");
        jsonObject.add("function", "deleteFile");
        jsonObject.add("filename",  fileName);
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * Download this file from the robot
     * @param event never used
     */
    @FXML
    void fileDownload(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Files");
        jsonObject.add("function", "downloadFile");
        jsonObject.add("filename",  fileName);
        sender.sendMessage(jsonObject.toJSONString());
    }

}
