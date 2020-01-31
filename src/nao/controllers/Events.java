package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import nao.sender;

/**
 * Events which are raised, not Speech, Face and Behavior
 */
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

    /**
     * Start and Stop the Barcode reader
     * @param event never used
     */
    @FXML
    void BarcodeReader(ActionEvent event) {
        if(isBarcodeReaderSelected){
            BarcodeReaderButton.setStyle("-fx-background-color: Red"); //set the colour of the button red
            isBarcodeReaderSelected = false; //set the boolean to false -> reader is turned off
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Barcode");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            BarcodeReaderButton.setStyle("-fx-background-color: Green"); //set the colour of the button green
            isBarcodeReaderSelected = true; //set the boolean to true -> reader is turned on
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Barcode");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * Start/Stop the Landmark reader
     * @param event never used
     */
    @FXML
    void Landmark(ActionEvent event) {
        if(isLandmarkSelected){
            LandmarkButton.setStyle("-fx-background-color: Red");//set the colour of the button red
            isLandmarkSelected = false; //set the boolean to false -> reader is turned off
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Landmark");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            LandmarkButton.setStyle("-fx-background-color: Green"); //set the colour of the button green
            isLandmarkSelected = true; //set the boolean to true -> reader is turned on
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Landmark");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * Start/Stop the Laser (returns the distance to the next object)
     * @param event never used
     */
    @FXML
    void Laser(ActionEvent event) {
        if(isLaserSelected){
            LaserButton.setStyle("-fx-background-color: Red"); //set the colour of the button red
            isLaserSelected = false; //set the boolean to false -> reader is turned off
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Laser");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            LaserButton.setStyle("-fx-background-color: Green"); //set the colour of the button green
            isLaserSelected = true; //set the boolean to true -> reader is turned on
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Laser");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * Start/Stop the FeetContact Event (raised if Nao isn't touching the ground)
     * @param event never used
     */
    @FXML
    void FeetContact(ActionEvent event) {
        if(isFeetContactButtonSelected){
            FeetContactButton.setStyle("-fx-background-color: Red"); //set the colour of the button red
            isFeetContactButtonSelected = false; //set the boolean to false -> reader is turned off
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FootContact");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            FeetContactButton.setStyle("-fx-background-color: Green"); //set the colour of the button green
            isFeetContactButtonSelected = true; //set the boolean to true -> reader is turned on
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "FootContact");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * Start/Stop the Sonar (returns distance to the next object)
     * @param event never used
     */
    @FXML
    void Sonar(ActionEvent event) {
        if(isSonarButtonSelected){
            SonarButton.setStyle("-fx-background-color: Red"); //set the colour of the button red
            isSonarButtonSelected = false; //set the boolean to false -> reader is turned off
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Sonar");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        } else{
            SonarButton.setStyle("-fx-background-color: Green"); //set the colour of the button green
            isSonarButtonSelected = true; //set the boolean to true -> reader is turned on
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Events");
            jsonObject.add("function", "Sonar");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

}
