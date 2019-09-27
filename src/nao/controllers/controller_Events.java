package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import nao.sender;


public class controller_Events {
    private boolean isFeetContactButtonSelected = false;


    @FXML
    private ToggleButton FeetContactButton;


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
}
