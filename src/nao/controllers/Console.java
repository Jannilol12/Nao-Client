package nao.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Console {


    @FXML
    private TextArea serverConsoleTextArea;

    @FXML
    private TextArea clientConsoleTextArea;

    public static Console c;

    public Console(){
        c = this;
    }

    public void setServerConsoleText(String text){
        serverConsoleTextArea.appendText(text);
        serverConsoleTextArea.appendText("\n");
    }

    public void setClientConsoleTextArea(String text){
        clientConsoleTextArea.appendText(text);
        clientConsoleTextArea.appendText("\n");
    }
}
