package nao.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Console {
    @FXML
    private TextArea ConsoleTextArea;
    public static Console c;

    public Console(){
        c = this;
    }

    public void setText(String text){
        ConsoleTextArea.appendText(text);
        ConsoleTextArea.appendText("\n");
    }
}
