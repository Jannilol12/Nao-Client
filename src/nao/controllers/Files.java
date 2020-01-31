package nao.controllers;

import components.json.JSONObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

/**
 * My own little FileZilla
 */
public class Files implements Initializable {
    private File file;
    private File directoryFile;
    private String fileName;
    public static Files cF;

    @FXML
    private ListView<String> fileListView;

    @FXML
    private Text filenameForUpload;

    @FXML
    private Text directorynameForDownloads;

    @FXML
    private Button fileUploadSelectButton;

    /**
     * This is used for the MainReceiver, so other classes can use methods
     */
    public Files(){
        cF = this;
    }

    /**
     * Initialize is like a constructor for JavaFX
     * @param url never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //raised if a new item is added to the list
        fileListView.setCellFactory(t -> new ListCell<String>() {
            @Override
            protected void updateItem(String node, boolean empty) {
                super.updateItem(node, empty);
                if(empty) { //if there is no item in the list
                    setGraphic(null);
                }else if(node != null) {
                    //add a new item (FilesListViewCell, which has its own fxml) to the list
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

        filenameForUpload.setText("Filename");
    }

    /**
     * reload the list
     * @param event never used
     */
    @FXML
    void directoryReload(ActionEvent event) {
        SendMessages.sendAllFiles();
    }

    /**
     * upload a file to the robot
     * @param event never used
     */
    @FXML
    void fileUpload(ActionEvent event) {
        //if there is no file selected, change the Border of the Button RED
        if(!filenameForUpload.getText().equalsIgnoreCase("Filename")) {
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
                while ((length = fileInputStream.read(bytes)) != -1) {
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
            //reload this list and the list from the audioPlayer
            SendMessages.sendAudioFiles();
            SendMessages.sendAllFiles();

            //reset the Text and the Button
            filenameForUpload.setText("Filename");
            fileUploadSelectButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            fileUploadSelectButton.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * Receiving a list from the server with all filenames and add them to the list
     * @param strings file names as a Lists
     */
    public void loadFiles(List<String> strings){
        Platform.runLater(() -> {
            fileListView.getItems().clear();
            fileListView.getItems().addAll(strings);
        });
    }

    /**
     * Select a file which shall be uploaded
     * @param event never used
     */
    @FXML
    public void fileUploadSelect(ActionEvent event) {
        //fileChooser to choose a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file!");
        file = fileChooser.showOpenDialog(MainFrame.stage);
        fileName = file.getName(); //get the name of the selected file
        filenameForUpload.setText(fileName);
    }

    /**
     * Select a directory in which the downloaded files shall be placed
     * @param event never used
     */
    @FXML
    void directorySelect(ActionEvent event) {
        //directoryChooser to choose a directory (download folder)
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory!");
        directoryFile = directoryChooser.showDialog(MainFrame.stage); //get the path of the directory
        directorynameForDownloads.setText(directoryFile.getName()); //get the name of the directory
    }

    /**
     * get the directory path, used by the {@link nao.MainReceiver} when downloading a file
     * @return the path of the directory
     */
    public File getDirectory(){
        return directoryFile;
    }

}
