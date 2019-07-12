package nao.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nao.sender;

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
        Gehirn.getItems().add("0");
        Gehirn.getItems().add("1");
        Gehirn.getItems().add("2");
        Gehirn.getItems().add("3");
        Gehirn.getItems().add("4");
        Gehirn.getItems().add("5");
        Gehirn.getItems().add("6");
        Gehirn.getItems().add("7");
        Gehirn.getItems().add("8");
        Gehirn.getItems().add("9");
        Gehirn.getItems().add("10");
        Gehirn.getItems().add("11");

        Ohr_Links.getItems().add("Alle");
        Ohr_Links.getItems().add("1");
        Ohr_Links.getItems().add("2");
        Ohr_Links.getItems().add("3");
        Ohr_Links.getItems().add("4");
        Ohr_Links.getItems().add("5");
        Ohr_Links.getItems().add("6");
        Ohr_Links.getItems().add("7");
        Ohr_Links.getItems().add("8");
        Ohr_Links.getItems().add("9");
        Ohr_Links.getItems().add("10");

        Ohr_Rechts.getItems().add("Alle");
        Ohr_Rechts.getItems().add("1");
        Ohr_Rechts.getItems().add("2");
        Ohr_Rechts.getItems().add("3");
        Ohr_Rechts.getItems().add("4");
        Ohr_Rechts.getItems().add("5");
        Ohr_Rechts.getItems().add("6");
        Ohr_Rechts.getItems().add("7");
        Ohr_Rechts.getItems().add("8");
        Ohr_Rechts.getItems().add("9");
        Ohr_Rechts.getItems().add("10");

        Auge_Links.getItems().add("Alle");
        Auge_Links.getItems().add("1");
        Auge_Links.getItems().add("2");
        Auge_Links.getItems().add("3");
        Auge_Links.getItems().add("4");
        Auge_Links.getItems().add("5");
        Auge_Links.getItems().add("6");
        Auge_Links.getItems().add("7");
        Auge_Links.getItems().add("8");

        Auge_Rechts.getItems().add("Alle");
        Auge_Rechts.getItems().add("1");
        Auge_Rechts.getItems().add("2");
        Auge_Rechts.getItems().add("3");
        Auge_Rechts.getItems().add("4");
        Auge_Rechts.getItems().add("5");
        Auge_Rechts.getItems().add("6");
        Auge_Rechts.getItems().add("7");
        Auge_Rechts.getItems().add("8");

        Fuss_Links.getItems().add("Alle");
        Fuss_Links.getItems().add("Blue");
        Fuss_Links.getItems().add("Green");
        Fuss_Links.getItems().add("Red");

        Fuss_Rechts.getItems().add("Alle");
        Fuss_Rechts.getItems().add("Blue");
        Fuss_Rechts.getItems().add("Green");
        Fuss_Rechts.getItems().add("Red");

        method.getItems().add("rgb");
        method.getItems().add("on");
        method.getItems().add("off");
    }

    @FXML
    void Start_btn(ActionEvent event) {
        try {
            if(!random_duration.getText().isEmpty()){
                sender.sendMessage("{\"type\":\"leds\",\"method\": \"random\", \"duration\":" + Float.parseFloat(random_duration.getText())+ "}");
            }else if(!rotate_duration.getText().isEmpty() && !rotate_rotation.getText().isEmpty()){
                int rgb = ((int) Colour.getValue().getRed() << 8*2) + ((int) Colour.getValue().getGreen() << 8) + (int) Colour.getValue().getBlue();
//                System.out.println(rgb + " " + Integer.toBinaryString(rgb));
                sender.sendMessage("{\"type\":\"leds\",\"method\": \"rotate\", \"rgbe\":" + rgb + ", \"timeR\":" + Float.parseFloat(rotate_rotation.getText()) + ", \"timeD\":" + Float.parseFloat(rotate_rotation.getText()) + "}");
            }else {
                if (Gehirn.getSelectionModel().getSelectedItem() != null) {
                    if (Gehirn.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 0; i < 12; i++) {
                            sender.sendMessage("{\"type\":\"leds\",\"method\": \"" + method.getSelectionModel().getSelectedItem() + "\", \"ledname\": \"Brain" + i + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        }
                    } else
                        sender.sendMessage("{\"type\":\"leds\",\"method\": \"" + method.getSelectionModel().getSelectedItem() + "\", \"ledname\": \"Brain" + Gehirn.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
                if (Ohr_Links.getSelectionModel().getSelectedItem() != null) {
                    if (Ohr_Links.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 11; i++) {
                            sender.sendMessage("{\"type\":\"leds\", \"method\":" + method.getSelectionModel().getSelectedItem() + ",\"ledname\": \"LeftEarLed" + i + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        }
                    } else
                        sender.sendMessage("{\"type\":\"leds\", \"method\":" + method.getSelectionModel().getSelectedItem() + ",\"ledname\": \"LeftEarLed" + Ohr_Links.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
                if (Ohr_Rechts.getSelectionModel().getSelectedItem() != null) {
                    if (Ohr_Rechts.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 11; i++) {
                            sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"RightEarLed" + i + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        }
                    } else
                        sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"RightEarLed" + Ohr_Rechts.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
                if (Auge_Links.getSelectionModel().getSelectedItem() != null) {
                    if (Auge_Links.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                            sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"LeftFaceLed" + i + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        }
                    } else
                        sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"LeftFaceLed" + Auge_Links.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
                if (Auge_Rechts.getSelectionModel().getSelectedItem() != null) {
                    if (Auge_Rechts.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        for (int i = 1; i < 9; i++) {
                            sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"RightFaceLed" + i + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        }
                    }
                    sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"RightFaceLed" + Auge_Rechts.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
                if (Fuss_Links.getSelectionModel().getSelectedItem() != null) {
                    if (Fuss_Links.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"LeftFootLedsBlue\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"LeftFootLedsGreen\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"LeftFootLedsRed\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                    } else
                        sender.sendMessage("{\"type\":\"leds\",\"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\": \"LeftFootLeds" + Fuss_Links.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
                if (Fuss_Rechts.getSelectionModel().getSelectedItem() != null) {
                    if (Fuss_Rechts.getSelectionModel().getSelectedItem().equalsIgnoreCase("Alle")) {
                        sender.sendMessage("{\"type\":\"leds\", \"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\":\"RightFootLedsBlue\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        sender.sendMessage("{\"type\":\"leds\", \"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\":\"RightFootLedsGreen\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                        sender.sendMessage("{\"type\":\"leds\", \"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\":\"RightFootLedsRed\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                    }
                    sender.sendMessage("{\"type\":\"leds\", \"method\":" + method.getSelectionModel().getSelectedItem() + ", \"ledname\":\"RightFootLeds" + Fuss_Rechts.getSelectionModel().getSelectedItem() + "\", \"red\":" + (float) Colour.getValue().getRed() + ", \"green\":" + Colour.getValue().getGreen() + ", \"blue\":" + Colour.getValue().getBlue() + ", \"Fade\":" + Float.parseFloat(Fade.getText()) + "}");
                }
            }
        }catch (NumberFormatException err){
            System.out.println("NumberFormatException");
            err.printStackTrace();
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
