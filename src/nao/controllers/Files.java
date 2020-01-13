package nao.controllers;

import components.json.JSONObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import nao.MainFrame;
import nao.SendMessages;
import nao.sender;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class Files implements Initializable {
    private File file;
    private File directoryFile;
    private String fileName;
    public static Files cF;

    public Files(){
        cF = this;
    }

    @FXML
    private ListView<String> fileListView;

    @FXML
    private Text filenameForUpload;

    @FXML
    private Text directorynameForDownloads;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileListView.setCellFactory(t -> new ListCell<String>() {
            @Override
            protected void updateItem(String node, boolean empty) {
                super.updateItem(node, empty);

                if(empty) {
                    setGraphic(null);
                }else if(node != null) {
                    FXMLLoader loader = new FXMLLoader();
                    FilesListViewCell con = new FilesListViewCell(node);
                    loader.setLocation(Files.class.getResource("/nao/fxml/FilesListViewCell.fxml"));
                    loader.setController(con);
                    Parent root;
                    try {
                        root = loader.load();
                        setGraphic(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                        setGraphic(null);
                    }
                }
            }
        });
    }

    @FXML
    void directoryReload(ActionEvent event) {
        SendMessages.sendAllFiles();
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
                jsonObject.add("type", "Files");
                jsonObject.add("function", "uploadFile");
                jsonObject.add("name", fileName);
                jsonObject.add("bytes", new String(base64, "UTF-8"));
                sender.sendMessage(jsonObject.toJSONString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SendMessages.sendAudioFiles();
        SendMessages.sendAllFiles();
    }

    public void loadFiles(List<String> strings){
        Platform.runLater(() -> {
            fileListView.getItems().clear();
            fileListView.getItems().addAll(strings);
        });
    }

    @FXML
    void fileUploadSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Hier du müssen Datei auswählen!");
        file = fileChooser.showOpenDialog(MainFrame.stage);
        fileName = file.getName();
        filenameForUpload.setText(fileName);
    }

    @FXML
    void directorySelect(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wähl a mal a Directory aus!");
        directoryFile = directoryChooser.showDialog(MainFrame.stage);
        directorynameForDownloads.setText(directoryFile.getName());
    }

    public File getDirectory(){
        return directoryFile;
    }

}
