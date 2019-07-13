package nao.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

import components.json.JSONObject;

public class controller_led implements Initializable {

    @FXML
    private TextField Fade;

    @FXML
    private ColorPicker Colour;

    @FXML
    private ComboBox<String> Gehirn;

    @FXML
    private ComboBox<String> Ohr_Links;

    @FXML
    private ComboBox<String> Ohr_Rechts;

    @FXML
    private ComboBox<String> Auge_Links;

    @FXML
    private ComboBox<String> Auge_Rechts;

    @FXML
    private ComboBox<String> Fuss_Links;

    @FXML
    private ComboBox<String> Fuss_Rechts;

    @FXML
    private ComboBox<String> method;

    @FXML
    private TextField random_duration;

    @FXML
    private TextField rotate_rotation;

    @FXML
    private TextField rotate_duration;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Gehirn.getItems().add("Alle");
        for(int i = 0; i <= 11; i++) 
        	Gehirn.getItems().add(i+"");

        Ohr_Links.getItems().add("Alle");
        for(int i = 1; i <= 10; i++) 
        	Ohr_Links.getItems().add(i+"");

        Ohr_Rechts.getItems().add("Alle");
        for(int i = 1; i <= 10; i++) 
        	Ohr_Rechts.getItems().add(i+"");

        Auge_Links.getItems().add("Alle");
        for(int i = 1; i <= 8; i++) 
        	Ohr_Rechts.getItems().add(i+"");

        Auge_Rechts.getItems().add("Alle");
        for(int i = 1; i <= 8; i++) 
        	Ohr_Rechts.getItems().add(i+"");

        Fuss_Links.getItems().addAll("Alle", "Blue", "Green", "Red");
        Fuss_Rechts.getItems().addAll("Alle", "Blue", "Green", "Red");
        method.getItems().addAll("rgb", "on", "off");
    }

    @FXML
    void Start_btn(ActionEvent event) {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.add("type", "leds");
    	
        try {
            if(!random_duration.getText().isEmpty()){
            	jsonObject.add("method", "random");
            	jsonObject.add("duration", Float.parseFloat(random_duration.getText()));
            	
            	sender.sendMessage(jsonObject.toJSONString());
            	
            } else if(!rotate_duration.getText().isEmpty() && !rotate_rotation.getText().isEmpty()){
                int rgb = (int) Colour.getValue().getRed() << 8*2 + (int) Colour.getValue().getGreen() << 8 + (int) Colour.getValue().getBlue();
                jsonObject.add("method", "rotate");
                jsonObject.add("rgbe", rgb);
                jsonObject.add("timeR", Float.parseFloat(rotate_rotation.getText()));
                jsonObject.add("timeD", Float.parseFloat(rotate_rotation.getText()));
                
                sender.sendMessage(jsonObject.toJSONString());
                
            } else {
            	jsonObject.add("method", method.getSelectionModel().getSelectedItem());
            	jsonObject.add("red", (float) Colour.getValue().getRed());
            	jsonObject.add("green", Colour.getValue().getGreen());
            	jsonObject.add("blue", Colour.getValue().getBlue());
            	jsonObject.add("Fade", Float.parseFloat(Fade.getText()));
            	
                if (Gehirn.getSelectionModel().getSelectedItem() != null) {
                    if (Gehirn.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 0; i < 12; i++) {
                        	jsonObject.add("ledname", "Brain" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "Brain" + Gehirn.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }   
                }
                
                if (Ohr_Links.getSelectionModel().getSelectedItem() != null) {
                    if (Ohr_Links.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 11; i++) {
                        	jsonObject.add("ledname", "LeftEarLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "LeftEarLed" + Ohr_Links.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (Ohr_Rechts.getSelectionModel().getSelectedItem() != null) {
                    if (Ohr_Rechts.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 11; i++) {
                        	jsonObject.add("ledname", "RightEarLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "RightEarLed" + Ohr_Rechts.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (Auge_Links.getSelectionModel().getSelectedItem() != null) {
                    if (Auge_Links.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                        	jsonObject.add("ledname", "LeftFaceLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "LeftFaceLed" + Auge_Links.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (Auge_Rechts.getSelectionModel().getSelectedItem() != null) {
                    if (Auge_Rechts.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                        	jsonObject.add("ledname", "RightFaceLed" + i);
                        	sender.sendMessage(jsonObject.toJSONString());
                        }
                    } else {
                    	jsonObject.add("ledname", "RightFaceLed" + Auge_Rechts.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (Fuss_Links.getSelectionModel().getSelectedItem() != null) {
                    if (Fuss_Links.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                    	jsonObject.add("ledname", "LeftFootLedsBlue");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "LeftFootLedsGreen");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "LeftFootLedsRed");
                    	sender.sendMessage(jsonObject.toJSONString());
                    } else {
                    	jsonObject.add("ledname", "LeftFootLeds" + Fuss_Links.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
                
                if (Fuss_Rechts.getSelectionModel().getSelectedItem() != null) {
                    if (Fuss_Rechts.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                    	jsonObject.add("ledname", "RightFootLedsBlue");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "RightFootLedsGreen");
                    	sender.sendMessage(jsonObject.toJSONString());
                    	
                    	jsonObject.add("ledname", "RightFootLedsRed");
                    	sender.sendMessage(jsonObject.toJSONString());
                    } else {
                    	jsonObject.add("ledname", "RightFootLeds" + Fuss_Rechts.getSelectionModel().getSelectedItem());
                    	sender.sendMessage(jsonObject.toJSONString());
                    }
                }
            }
        }catch (NumberFormatException err){
            System.out.println("NumberFormatException");
        }

        Gehirn.getSelectionModel().clearSelection();
        Gehirn.setPromptText("Gehirn");
        Ohr_Rechts.getSelectionModel().clearSelection();
        Ohr_Rechts.setPromptText("Ohr Rechts");
        Ohr_Links.getSelectionModel().clearSelection();
        Ohr_Links.setPromptText("Ohr Links");
        Auge_Rechts.getSelectionModel().clearSelection();
        Auge_Rechts.setPromptText("Auge Rechts");
        Auge_Links.getSelectionModel().clearSelection();
        Auge_Links.setPromptText("Auge Links");
        Fuss_Rechts.getSelectionModel().clearSelection();
        Fuss_Rechts.setPromptText("Fuss Rechts");
        Fuss_Links.getSelectionModel().clearSelection();
        Fuss_Links.setPromptText("Fuss Links");
    }

}
