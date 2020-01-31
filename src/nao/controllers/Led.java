package nao.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import nao.events.ButtonLongPress;
import nao.events.PromptButtonCell;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;

/**
 * Control the LEDs from the nao
 */
public class Led implements Initializable {

    @FXML
    private TextField fade;

    @FXML
    private ColorPicker colour;

    @FXML
    private ComboBox<String> brain;

    @FXML
    private ComboBox<String> leftEar;

    @FXML
    private ComboBox<String> rightEar;

    @FXML
    private ComboBox<String> leftEye;

    @FXML
    private ComboBox<String> rightEye;

    @FXML
    private ComboBox<String> leftFoot;

    @FXML
    private ComboBox<String> rightFoot;

    @FXML
    private ComboBox<String> method;

    @FXML
    private TextField durationEyesRandom;

    @FXML
    private TextField rotationEyesRotate;

    @FXML
    private TextField durationEyesRotate;

    /**
     * Initialize is like a constructor for JavaFX
     * @param url            never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //add the amount of LEDs the brain has and "All" to control them all at once
        brain.getItems().add("All");
        for(int i = 0; i <= 11; i++)
        	brain.getItems().add(i+"");

        leftEar.getItems().add("All");
        for(int i = 1; i <= 10; i++)
        	leftEar.getItems().add(i+"");

        rightEar.getItems().add("All");
        for(int i = 1; i <= 10; i++)
        	rightEar.getItems().add(i+"");

        leftEye.getItems().add("All");
        for(int i = 1; i <= 8; i++)
        	leftEye.getItems().add(i+"");

        rightEye.getItems().add("All");
        for(int i = 1; i <= 8; i++)
        	rightEye.getItems().add(i+"");

        //the feet have different LEDs to control
        leftFoot.getItems().addAll("All", "Blue", "Green", "Red");
        rightFoot.getItems().addAll("All", "Blue", "Green", "Red");

        //turn the LEDs on/off or rgb mode
        method.getItems().addAll("rgb", "on", "off");

        //because the prompt text from a ComboBox is deleted after selected something,
        // and if you clear the selection the text also won't come back,
        // I copied a Listener from StackOverFlow
        brain.setPromptText("Brain");
        brain.setButtonCell(new PromptButtonCell<>("Brain"));
        leftFoot.setPromptText("Left foot");
        leftFoot.setButtonCell(new PromptButtonCell<>("Left foot"));
        rightFoot.setPromptText("Right foot");
        rightFoot.setButtonCell(new PromptButtonCell<>("Right foot"));
        leftEar.setPromptText("Left ear");
        leftEar.setButtonCell(new PromptButtonCell<>("Left ear"));
        rightEar.setPromptText("Right ear");
        rightEar.setButtonCell(new PromptButtonCell<>("Right ear"));
        leftEye.setPromptText("Left eye");
        leftEye.setButtonCell(new PromptButtonCell<>("Left eye"));
        rightEye.setPromptText("Right eye");
        rightEye.setButtonCell(new PromptButtonCell<>("Right eye"));
        method.setPromptText("Method");
        method.setButtonCell(new PromptButtonCell<>("Method"));
    }

    /**
     * Start the things you selected
     * @param event never used
     */
    @FXML
    void startButton(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.add("type", "Leds");

    	//if something went wrong, set the border of the things which went wrong RED
        try {
            //if randomEyes is given, use this function and no others
            if(!durationEyesRandom.getText().isEmpty()){
            	jsonObject.add("method", "random");
            	jsonObject.add("duration", Float.parseFloat(durationEyesRandom.getText()));
            	sender.sendMessage(jsonObject.toJSONString());

            //else if rotate Eyes is given, use this function and not turning single LEDs on/off/rgb
            } else if(!durationEyesRotate.getText().isEmpty() && !rotationEyesRotate.getText().isEmpty()){
                //because the API of the nao is a little bit dumb
                // you can't give the red, green and blue colour as different values...
                // you have to calculate them to one Integer
                int rgb = (int) colour.getValue().getRed() << 8*2 + (int) colour.getValue().getGreen() << 8 + (int) colour.getValue().getBlue();
                jsonObject.add("method", "rotate");
                jsonObject.add("method", "rotate");
                jsonObject.add("rgbe", rgb);
                jsonObject.add("timeR", Float.parseFloat(rotationEyesRotate.getText()));
                jsonObject.add("timeD", Float.parseFloat(rotationEyesRotate.getText()));
                sender.sendMessage(jsonObject.toJSONString());

            //else turn the LEDs which are selected on/off/rgb
            } else {
                if(method.getSelectionModel().getSelectedItem() == null){
                    //the fade is right, but no method is selected, so throw an Exception
                    throw new Exception("no method selected!");
                }
                //add the method, the colour and the fade to the message
            	jsonObject.add("method", method.getSelectionModel().getSelectedItem());
            	jsonObject.add("red", (float) colour.getValue().getRed());
            	jsonObject.add("green", colour.getValue().getGreen());
            	jsonObject.add("blue", colour.getValue().getBlue());
            	jsonObject.add("Fade", Float.parseFloat(fade.getText()));

            	//if brain is selected
                if (brain.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected send 11 messages, for each LED one message
                    if (brain.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        for (int i = 0; i < 12; i++) {
                        	jsonObject.add("ledname", "Brain" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    //else send one message with the one LED which is selected
                    } else {
                    	jsonObject.add("ledname", "Brain" + brain.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }

                //if leftEar is selected
                if (leftEar.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected send 10 messages, for each LED one message
                    if (leftEar.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        for (int i = 1; i < 11; i++) {
                        	jsonObject.add("ledname", "LeftEarLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                        //else send one message with the one LED which is selected
                    } else {
                    	jsonObject.add("ledname", "LeftEarLed" + leftEar.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }

                //if rightEar is selected
                if (rightEar.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected send 10 messages, for each LED one message
                    if (rightEar.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        for (int i = 1; i < 11; i++) {
                        	jsonObject.add("ledname", "RightEarLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                     //else send one message with the one LED which is selected
                    } else {
                    	jsonObject.add("ledname", "RightEarLed" + rightEar.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }

                //if leftEye is selected
                if (leftEye.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected send 8 messages, for each LED one message
                    if (leftEye.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                        	jsonObject.add("ledname", "LeftFaceLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    //else send one message with the one LED which is selected
                    } else {
                    	jsonObject.add("ledname", "LeftFaceLed" + leftEye.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }

                //if rightEye is selected
                if (rightEye.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected send 8 messages, for each LED one message
                    if (rightEye.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        for (int i = 1; i < 9; i++) {
                        	jsonObject.add("ledname", "RightFaceLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    //else send one message with the one LED which is selected
                    } else {
                    	jsonObject.add("ledname", "RightFaceLed" + rightEye.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }

                //if leftFoot is selected
                if (leftFoot.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected
                    if (leftFoot.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        //send the colour for the blue LED
                    	jsonObject.add("ledname", "LeftFootLedsBlue");
                    	sender.sendMessage(jsonObject.toJSONString());

                        //send the colour for the green LED
                        jsonObject.add("ledname", "LeftFootLedsGreen");
                    	sender.sendMessage(jsonObject.toJSONString());

                        //send the colour for the red LED
                        jsonObject.add("ledname", "LeftFootLedsRed");
                    	sender.sendMessage(jsonObject.toJSONString());
                    } else {
                    	jsonObject.add("ledname", "LeftFootLeds" + leftFoot.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }

                //if rightFoot is selected
                if (rightFoot.getSelectionModel().getSelectedItem() != null) {
                    //if all is selected
                    if (rightFoot.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
                        //send the colour for the blue LED
                    	jsonObject.add("ledname", "RightFootLedsBlue");
                    	sender.sendMessage(jsonObject.toJSONString());

                        //send the colour for the green LED
                    	jsonObject.add("ledname", "RightFootLedsGreen");
                    	sender.sendMessage(jsonObject.toJSONString());

                        //send the colour for the red LED
                    	jsonObject.add("ledname", "RightFootLedsRed");
                    	sender.sendMessage(jsonObject.toJSONString());
                    } else {
                    	jsonObject.add("ledname", "RightFootLeds" + rightFoot.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
            }
            //reset the selections, text fields and the colour of the borders
            brain.getSelectionModel().clearSelection();
            rightEar.getSelectionModel().clearSelection();
            leftEar.getSelectionModel().clearSelection();
            rightEye.getSelectionModel().clearSelection();
            leftEye.getSelectionModel().clearSelection();
            rightFoot.getSelectionModel().clearSelection();
            leftFoot.getSelectionModel().clearSelection();
            method.getSelectionModel().clearSelection();
            fade.clear();
            rotationEyesRotate.clear();
            durationEyesRotate.clear();
            durationEyesRandom.clear();

            fade.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            method.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            rotationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            durationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }catch (Exception err){
            //if nothing is written into fade and no method is selected,
            // I assume that there are LEDs selected which shall be turned on/off or into RGB
            if(method.getSelectionModel().getSelectedItem() == null && fade.getText().equalsIgnoreCase("") && rotationEyesRotate.getText().equalsIgnoreCase("") && durationEyesRotate.getText().equalsIgnoreCase("")){
                fade.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                method.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                rotationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                durationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));

            //fade is right, but no method is selected
            } else if(method.getSelectionModel().getSelectedItem() == null && rotationEyesRotate.getText().equalsIgnoreCase("") && durationEyesRotate.getText().equalsIgnoreCase("")){
                method.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                fade.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                rotationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                durationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));

            //method is selected but no fade
            }else if(fade.getText().equalsIgnoreCase("") && rotationEyesRotate.getText().equalsIgnoreCase("") && durationEyesRotate.getText().equalsIgnoreCase("")){
                fade.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                method.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                rotationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                durationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));

            //at rotate Eyes the duration is given, but no amount of rotations
            } else if(rotationEyesRotate.getText().equalsIgnoreCase("") && !durationEyesRotate.getText().equalsIgnoreCase("")){
                rotationEyesRotate.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                method.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                fade.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                durationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));

            //at rotate Eyes the amount of rotations is given, but no duration
            } else if(!rotationEyesRotate.getText().equalsIgnoreCase("") && durationEyesRotate.getText().equalsIgnoreCase("")){
                durationEyesRotate.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                method.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                fade.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
                rotationEyesRotate.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
            }
        }
    }

}
