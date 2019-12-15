package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import nao.sender;

public class Events {
    private boolean isFeetContactButtonSelected = false;
    private boolean isSonarButtonSelected = false;
    private boolean isBarcodeReaderSelected = false;
    private boolean isLandmarkSelected = false;
    private boolean isLaserSelected = false;

    @FXML
    private ToggleButton FeetContactButton;

    @FXML
    private ToggleButton SonarButton;

    @FXML
    private Button BarcodeReaderButton;

    @FXML
    private Button LandmarkButton;

    @FXML
    private Button LaserButton;

    @FXML
    void BarcodeReader(ActionEvent event) {
        if(isBarcodeReaderSelected){
            BarcodeReaderButton.setStyle("-fx-background-color: Red");
            isBarcodeReaderSelected = false;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Barcode");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            BarcodeReaderButton.setStyle("-fx-background-color: Green");
            isBarcodeReaderSelected = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Barcode");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void Landmark(ActionEvent event) {
        if(isLandmarkSelected){
            LandmarkButton.setStyle("-fx-background-color: Red");
            isLandmarkSelected = false;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Landmark");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            LandmarkButton.setStyle("-fx-background-color: Green");
            isLandmarkSelected = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Landmark");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void Laser(ActionEvent event) {
        if(isLaserSelected){
            LaserButton.setStyle("-fx-background-color: Red");
            isLaserSelected = false;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Laser");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            LaserButton.setStyle("-fx-background-color: Green");
            isLaserSelected = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Laser");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void FeetContact(ActionEvent event) {
        if(isFeetContactButtonSelected){
            FeetContactButton.setStyle("-fx-background-color: Red");
            isFeetContactButtonSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FootContact");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            FeetContactButton.setStyle("-fx-background-color: Green");
            isFeetContactButtonSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FootContact");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    @FXML
    void Sonar(ActionEvent event) {
        if(isSonarButtonSelected){
            SonarButton.setStyle("-fx-background-color: Red");
            isSonarButtonSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Sonar");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            SonarButton.setStyle("-fx-background-color: Green");
            isSonarButtonSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Sonar");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

}
