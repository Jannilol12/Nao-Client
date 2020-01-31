package nao.controllers;

import components.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import nao.sender;

/**
 * Control the system of the robot and see all the temperatures of the motors and the battery level
 */
public class RobotSystem {
    @FXML
    private Text batteryLevel;

    @FXML
    private Text battery;

    @FXML
    private Text lHand;

    @FXML
    private Text headCPU;

    @FXML
    private Text headYaw;

    @FXML
    private Text lElbowYaw;

    @FXML
    private Text lShoulderPitch;

    @FXML
    private Text rHand;

    @FXML
    private Text lHipPitch;

    @FXML
    private Text headPitch;

    @FXML
    private Text lElbowRoll;

    @FXML
    private Text lShoulderRoll;

    @FXML
    private Text lWristYaw;

    @FXML
    private Text rWristYaw;

    @FXML
    private Text lHipRoll;

    @FXML
    private Text lKneePitch;

    @FXML
    private Text rElbowYaw;

    @FXML
    private Text rElbowRoll;

    @FXML
    private Text rShoulderPitch;

    @FXML
    private Text rShoulderRoll;

    @FXML
    private Text rHipPitch;

    @FXML
    private Text rHipRoll;

    @FXML
    private Text lHipYawPitch;

    @FXML
    private Text lAnklePitch;

    @FXML
    private Text rHipYawPitch;

    @FXML
    private Text lAnkleRoll;

    @FXML
    private Text rAnklePitch;

    @FXML
    private Text rAnkleRoll;

    @FXML
    private Text rKneePitch;

    public static RobotSystem cc;

    /**
     * This is used for the MainReceiver, so other classes can use methods
     */
    public RobotSystem(){
        cc = this;
    }

    /**
     * set the battery load
     * @param Text how full the battery is
     */
    public void setBatteryText(int Text){
        batteryLevel.setText(Text + "");
    }

    /**
     * set ALL temperatures
     */
    public void setTemperatureText(String battery,
                                   String lHand,
                                   String headCPU,
                                   String headYaw,
                                   String lElbowYaw,
                                   String lShoulderPitch,
                                   String rHand,
                                   String lHipPitch,
                                   String headPitch,
                                   String lElbowRoll,
                                   String lShoulderRoll,
                                   String lWristYaw,
                                   String rWristYaw,
                                   String lHipRoll,
                                   String lKneePitch,
                                   String rElbowYaw,
                                   String rElbowRoll,
                                   String rShoulderPitch,
                                   String rShoulderRoll,
                                   String rHipPitch,
                                   String rHipRoll,
                                   String lHipYawPitch,
                                   String lAnklePitch,
                                   String rHipYawPitch,
                                   String lAnkleRoll,
                                   String rAnklePitch,
                                   String rAnkleRoll,
                                   String rKneePitch){

        this.battery.setText(battery);
        this.lHand.setText(lHand);
        this.headCPU.setText(headCPU);
        this.headYaw.setText(headYaw);
        this.lElbowYaw.setText(lElbowYaw);
        this.lShoulderPitch.setText(lShoulderPitch);
        this.rHand.setText(rHand);
        this.lHipPitch.setText(lHipPitch);
        this.headPitch.setText(headPitch);
        this.lElbowRoll.setText(lElbowRoll);
        this.lShoulderRoll.setText(lShoulderRoll);
        this.lWristYaw.setText(lWristYaw);
        this.rWristYaw.setText(rWristYaw);
        this.lHipRoll.setText(lHipRoll);
        this.lKneePitch.setText(lKneePitch);
        this.rElbowYaw.setText(rElbowYaw);
        this.rElbowRoll.setText(rElbowRoll);
        this.rShoulderPitch.setText(rShoulderPitch);
        this.rShoulderRoll.setText(rShoulderRoll);
        this.rHipPitch.setText(rHipPitch);
        this.rHipRoll.setText(rHipRoll);
        this.lHipYawPitch.setText(lHipYawPitch);
        this.lAnklePitch.setText(lAnklePitch);
        this.rHipYawPitch.setText(rHipYawPitch);
        this.lAnkleRoll.setText(lAnkleRoll);
        this.rAnklePitch.setText(rAnklePitch);
        this.rAnkleRoll.setText(rAnkleRoll);
        this.rKneePitch.setText(rKneePitch);
    }

    /**
     * reboot the robot
     * @param event never used
     */
    @FXML
    void systemReboot(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Reboot");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * shutdown the robot
     * @param event never used
     */
    @FXML
    void systemShutdown(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Shutdown");
        sender.sendMessage(jsonObject.toJSONString());
    }

}
