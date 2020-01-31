package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nao.events.PromptButtonCell;
import nao.sender;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * set the Autonomous Life mode of the nao
 */
public class AutonomousLife implements Initializable {
    @FXML
    private ComboBox<String> LifeMode;

    @FXML
    private TextField OffSetFromFloor;

    @FXML
    private Text ActualLifeMode;

    @FXML
    private Text OffsetFromFloor;

    @FXML
    private Text ExpressiveListening;

    @FXML
    private Text BackgroundStrategy;

    @FXML
    private ComboBox<String> StrategyList;

    @FXML
    private Button SetExpressiveListeningButton;

    private boolean isSetExpressiveListeningSelected = false;

    /**
     * Initialize is like a constructor for JavaFX
     * @param url never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize the ComboBoxes
        LifeMode.getItems().add("solitary");
        LifeMode.getItems().add("interactive");
        LifeMode.getItems().add("disabled");
        LifeMode.getItems().add("safeguard");
        StrategyList.getItems().add("none");
        StrategyList.getItems().add("backToNeutral");

        //because the prompt text from a ComboBox is deleted after selected something,
        // and if you clear the selection the text also won't come back,
        // I copied a Listener from StackOverFlow
        LifeMode.setPromptText("Select mode");
        LifeMode.setButtonCell(new PromptButtonCell<>("Select mode"));
        StrategyList.setPromptText("Select strategy");
        StrategyList.setButtonCell(new PromptButtonCell<>("Select strategy"));
    }

    /**
     * Set the background strategy of the nao
     * @param event never used
     */
    @FXML
    void SetBackgroundStrategy(ActionEvent event) {
        //if nothing selected, set the Border of the ComboBox RED
        if(StrategyList.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "BackgroundStrategy");
            jsonObject.add("Strategy", StrategyList.getSelectionModel().getSelectedItem());
            sender.sendMessage(jsonObject.toJSONString());
            //reset the ComboBox and the Border
            StrategyList.getSelectionModel().clearSelection();
            StrategyList.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            StrategyList.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * Start/Stop the Expressive Listening of the nao
     * @param event never used
     */
    @FXML
    void SetExpressiveListening(ActionEvent event) {
        //stop
        if(isSetExpressiveListeningSelected){
            //set the Colour of the Button Red
            SetExpressiveListeningButton.setStyle("-fx-background-color: Red");
            //boolean is used for turning it on/off -> true/false
            isSetExpressiveListeningSelected = false;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "ExpressiveListening");
            jsonObject.add("boolean",  "false");
            sender.sendMessage(jsonObject.toJSONString());
        //start
        } else{
            SetExpressiveListeningButton.setStyle("-fx-background-color: Green");
            isSetExpressiveListeningSelected = true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "ExpressiveListening");
            jsonObject.add("boolean",  "true");
            sender.sendMessage(jsonObject.toJSONString());
        }
    }

    /**
     * Set the Life Mode of the nao
     * @param event never used
     */
    @FXML
    void SetLifeMode(ActionEvent event) {
        //if nothing selected set the Border of the ComboBox RED
        if(LifeMode.getSelectionModel().getSelectedItem() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "LifeMode");
            jsonObject.add("Mode", LifeMode.getSelectionModel().getSelectedItem());
            sender.sendMessage(jsonObject.toJSONString());
            //reset the ComboBox and the Border
            LifeMode.getSelectionModel().clearSelection();
            LifeMode.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        }else{
            LifeMode.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }

    /**
     * How high the robot is standing from the floor
     * @param event never used
     */
    @FXML
    void setRobotOffsetFromFloor(ActionEvent event) {
        //if Text is not a Float set the Border of the TextField RED
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.add("type", "AutoLife");
            jsonObject.add("function", "RobotOffsetFromFloor");
            jsonObject.add("Offset", Float.parseFloat(OffSetFromFloor.getText()));
            sender.sendMessage(jsonObject.toJSONString());
            //reset the Text and Border
            OffSetFromFloor.setText("");
            OffSetFromFloor.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        } catch(NumberFormatException err){
            OffSetFromFloor.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
        }
    }
}
