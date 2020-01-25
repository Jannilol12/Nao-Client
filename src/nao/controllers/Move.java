package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import nao.events.PromptButtonCell;
import nao.sender;

public class Move implements Initializable {
	@FXML
    private TextField Steps;

    @FXML
    private CheckBox checkSteps;
    
    @FXML
    private TextField degree;

    @FXML
    private ComboBox<String> postureSelect;

    @FXML
    private TextField PostureSpeed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postureSelect.getItems().addAll("Stand", "StandInit", "StandZero", "Crouch", "Sit", "SitRelax", "LyingBelly", "LyingBack");

        postureSelect.setPromptText("Select a posture");
        postureSelect.setButtonCell(new PromptButtonCell<>("Select a posture"));
    }

    @FXML
    void wakeUp(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Wakeup\"}");
    }
	
	@FXML
    void forward(ActionEvent event) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Forward");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }
	
	@FXML
    void backwards(ActionEvent event) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Backward");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void left(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Left");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void right(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Right");
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void stop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Stop");
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void rotate(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Rotate");
        jsonObject.add("value", Integer.parseInt(degree.getText()));
        sender.sendMessage(jsonObject.toJSONString());
    }

    @FXML
    void PostureStart(ActionEvent event) {
        try {
            float postureSpeed = Float.parseFloat(PostureSpeed.getText());
            if(postureSelect.getSelectionModel().getSelectedItem() != null) {
                postureSelect.getSelectionModel().clearSelection();
                JSONObject jsonObject = new JSONObject();
                jsonObject.add("type", "Posture");
                jsonObject.add("position", postureSelect.getSelectionModel().getSelectedItem());
                jsonObject.add("speed", postureSpeed);
                sender.sendMessage(jsonObject.toJSONString());
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                postureSelect.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            }else{
                throw new NullPointerException("Didn't selected a posture!");
            }
        } catch(NumberFormatException err){
            if(postureSelect.getSelectionModel().getSelectedItem() == null){
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                postureSelect.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
            } else{
                postureSelect.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
            }
        }
        catch (NullPointerException err){
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                postureSelect.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }
}
