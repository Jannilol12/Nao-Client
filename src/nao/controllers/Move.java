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

/**
 * Move, rotate or put the robot into a posture
 */
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

    /**
     * Initialize is like a constructor for JavaFX
     * @param url            never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //add the possible postures
        postureSelect.getItems().addAll("Stand", "StandInit", "StandZero", "Crouch", "Sit", "SitRelax", "LyingBelly", "LyingBack");

        //because the prompt text from a ComboBox is deleted after selected something,
        // and if you clear the selection the text also won't come back,
        // I copied a Listener from StackOverFlow
        postureSelect.setPromptText("Select a posture");
        postureSelect.setButtonCell(new PromptButtonCell<>("Select a posture"));
    }

    /**
     * let the robot stand up
     * @param event never used
     */
    @FXML
    void wakeUp(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Wakeup");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * move forward
     * @param event
     */
	@FXML
    void forward(ActionEvent event) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Forward");
		//if "walk steps" is enabled -> send the amount of steps which are given, else send "0" -> infinity
		jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * move backwards
     * @param event never used
     */
	@FXML
    void backwards(ActionEvent event) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Backward");
        //if "walk steps" is enabled -> send the amount of steps which are given, else send "0" -> infinity
        jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * move left
     * @param event never used
     */
    @FXML
    void left(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Left");
        //if "walk steps" is enabled -> send the amount of steps which are given, else send "0" -> infinity
        jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * move right
     * @param event never used
     */
    @FXML
    void right(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.add("type", "Right");
        //if "walk steps" is enabled -> send the amount of steps which are given, else send "0" -> infinity
        jsonObject.add("value", checkSteps.isSelected() ? Integer.parseInt(Steps.getText()) + "" : "0");
		sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * STOP everything
     * @param event never used
     */
    @FXML
    void stop(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Stop");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * rotate the robot
     * @param event never used
     */
    @FXML
    void rotate(ActionEvent event) {
        //if no degrees are given, or not as an float, change the Border of the TextField RED
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "Rotate");
            jsonObject.add("value", Integer.parseInt(degree.getText()));
            sender.sendMessage(jsonObject.toJSONString());
            degree.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } catch(NumberFormatException err){
            degree.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * put the robot into a posture
     * @param event never used
     */
    @FXML
    void PostureStart(ActionEvent event) {
        //if no speed is given -> throw NumberFormatException, if no posture is selected -> throw NullPointerException
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
            //if also no posture is selected, change Border of the TextField and the ComboBox RED
            if(postureSelect.getSelectionModel().getSelectedItem() == null){
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                postureSelect.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
            //else change only the TextField border RED
            } else{
                postureSelect.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
            }
        }
        catch (NullPointerException err){
            //only change the ComboBox border RED
                PostureSpeed.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                postureSelect.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }
}
