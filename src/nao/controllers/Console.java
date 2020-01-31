package nao.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import nao.debugger.Debugger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Console for the Client and the Server
 */
public class Console implements Initializable {

    @FXML
    private TextArea serverConsoleTextArea;

    @FXML
    private TextArea clientConsoleTextArea;

    public static Console c;

    /**
     * This is used for the MainReceiver, so other classes can use methods
     */
    public Console(){
        c = this;
    }

    /**
     * Initialize is like a constructor for JavaFX
     * @param url            never used
     * @param resourceBundle never used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Debugger.getLogFilesDirectory());
    }

    /**
     * Write something in the Console of the Server
     * @param text String which shall be written into the Console
     */
    public void setServerConsoleText(String text){
        serverConsoleTextArea.appendText(text);
        serverConsoleTextArea.appendText("\n");
    }

    /**
     * Write something in the Console of the Client
     * @param text String which shall be written into the Console
     */
    public void setClientConsoleTextArea(String text){
        clientConsoleTextArea.appendText(text);
        clientConsoleTextArea.appendText("\n");
    }
}
