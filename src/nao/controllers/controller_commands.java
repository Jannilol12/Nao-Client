package nao.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import nao.sender;

public class controller_commands{
    public static controller_commands cc;

    public controller_commands(){
        cc = this;

    }

    public void setBatteryText(int Text){
        BatteryLoad.setText(Text + "");
    }

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

        Battery.setText(battery);
        LHand.setText(lHand);
        HeadCPU.setText(headCPU);
        HeadYaw.setText(headYaw);
        LElbowYaw.setText(lElbowYaw);
        LShoulderPitch.setText(lShoulderPitch);
        RHand.setText(rHand);
        LHipPitch.setText(lHipPitch);
        HeadPitch.setText(headPitch);
        LElbowRoll.setText(lElbowRoll);
        LShoulderRoll.setText(lShoulderRoll);
        LWristYaw.setText(lWristYaw);
        RWristYaw.setText(rWristYaw);
        LHipRoll.setText(lHipRoll);
        LKneePitch.setText(lKneePitch);
        RElbowYaw.setText(rElbowYaw);
        RElbowRoll.setText(rElbowRoll);
        RShoulderPitch.setText(rShoulderPitch);
        RShoulderRoll.setText(rShoulderRoll);
        RHipPitch.setText(rHipPitch);
        RHipRoll.setText(rHipRoll);
        LHipYawPitch.setText(lHipYawPitch);
        LAnklePitch.setText(lAnklePitch);
        RHipYawPitch.setText(rHipYawPitch);
        LAnkleRoll.setText(lAnkleRoll);
        RAnklePitch.setText(rAnklePitch);
        RAnkleRoll.setText(rAnkleRoll);
        RKneePitch.setText(rKneePitch);
    }

    @FXML
    private Text BatteryLoad;

    @FXML
    private Text Battery;

    @FXML
    private Text LHand;

    @FXML
    private Text HeadCPU;

    @FXML
    private Text HeadYaw;

    @FXML
    private Text LElbowYaw;

    @FXML
    private Text LShoulderPitch;

    @FXML
    private Text RHand;

    @FXML
    private Text LHipPitch;

    @FXML
    private Text HeadPitch;

    @FXML
    private Text LElbowRoll;

    @FXML
    private Text LShoulderRoll;

    @FXML
    private Text LWristYaw;

    @FXML
    private Text RWristYaw;

    @FXML
    private Text LHipRoll;

    @FXML
    private Text LKneePitch;

    @FXML
    private Text RElbowYaw;

    @FXML
    private Text RElbowRoll;

    @FXML
    private Text RShoulderPitch;

    @FXML
    private Text RShoulderRoll;

    @FXML
    private Text RHipPitch;

    @FXML
    private Text RHipRoll;

    @FXML
    private Text LHipYawPitch;

    @FXML
    private Text LAnklePitch;

    @FXML
    private Text RHipYawPitch;

    @FXML
    private Text LAnkleRoll;

    @FXML
    private Text RAnklePitch;

    @FXML
    private Text RAnkleRoll;

    @FXML
    private Text RKneePitch;

    @FXML
    void Reboot(ActionEvent event) {
        sender.sendMessage("{\"type\":\"reboot\"}");
    }

    @FXML
    void Shutdown(ActionEvent event) {
        sender.sendMessage("{\"type\":\"shutdown\"}");
    }
}
