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
        Battery.setText(Text + "");
    }

    @FXML
    private Text Battery;

    @FXML
    void Reboot(ActionEvent event) {
        sender.sendMessage("{\"type\":\"reboot\"}");
    }

    @FXML
    void Shutdown(ActionEvent event) {
        sender.sendMessage("{\"type\":\"shutdown\"}");
    }
}
