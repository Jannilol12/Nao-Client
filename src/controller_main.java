import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import components.json.JSONArray;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class controller_main implements Initializable {
    @FXML
    private TextField Steps;

    @FXML
    private CheckBox checkSteps;

    @FXML
    private ListView<Zwischenspeicher> list;

    @FXML
    private TextField degree;

    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    @FXML
    private ImageView connected;
    private boolean connect_image;

    public static controller_main cmain;

    public void clearProgs() {
        if(Platform.isFxApplicationThread())
            list.getItems().clear();
        else
            Platform.runLater(() -> {
                list.getItems().clear();
            });
    }

    public void addProg(Zwischenspeicher prog){
        if(Platform.isFxApplicationThread())
            list.getItems().add(prog);
        else
            Platform.runLater(() -> {
                list.getItems().add(prog);
            });
    }

    @FXML
    void backwards(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Backwards\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Backwards\", \"value\":\"0\"}");
        }
    }

    @FXML
    void connect(ActionEvent event) {
        try {
            sender.connected(ip.getText(), Integer.parseInt(port.getText()));

        }
        catch(Exception err) {}
    }


    @FXML
    void forward(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Forward\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Forward\", \"value\":\"0\"}");
        }
    }

    @FXML
    void left(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Left\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Left\", \"value\":\"0\"}");
        }
    }

    @FXML
    void right(ActionEvent event) {
        if(checkSteps.isSelected()){
            sender.sendMessage("{\"type\":\"Right\", \"value\":\"" + Integer.parseInt(Steps.getText()) +"\"");
        }
        else {
            sender.sendMessage("{\"type\":\"Right\", \"value\":\"0\"}");
        }
    }

    @FXML
    void stop(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Stop\", \"value\":\"0\"}");
    }

    @FXML
    void rotate(ActionEvent event) {
        sender.sendMessage("{\"type\":\"Rotate\", \"value\":\"" + Integer.parseInt(degree.getText()) +"\"}");
    }

    @FXML
    void destroy(ActionEvent event) {
        sender.destroy();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmain = this;

        list.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Zwischenspeicher prog = list.getSelectionModel().getSelectedItem();
                if(prog != null) {
                	if(prog.getInputArgs() == null)
                		sender.sendMessage("{\"type\":\"RunP\", \"value\":\"" + prog.name +"\"}");
                	else {
                		
                		Alert alert = new Alert(AlertType.CONFIRMATION);
        	            DialogPane root = alert.getDialogPane();

        	            for (ButtonType buttonType : root.getButtonTypes()) {
        	                ButtonBase button = (ButtonBase) root.lookupButton(buttonType);
        	                button.setOnAction(evt -> {
        	                    alert.close();
        	                });
        	            }
        	            
        	            alert.setHeaderText("Arguments Required");
        	            
        	            //TODO add Inputs
        	            JSONArray inputArray = prog.getInputArgs();
        	            
        	            for(Object obj : inputArray.getList()) {
        	            	//TODO
        	            }
        	            
        	            
        	            
        	            Optional<ButtonType> result = alert.showAndWait();
        	            if(result.get() == ButtonType.OK); //TODO send Command
        	            
                	}
                }
            }
        });

        new Thread( () -> {
            while(true) {
                if (sender.socket == null || sender.socket.isClosed()) {
                    if(connect_image != false) {
                        connected.setImage(new Image(controller_main.class.getResourceAsStream("/icons/getrennt.png")));
                        connect_image = false;
                    }
                } else {
                    if(!connect_image) {
                        connected.setImage(new Image(controller_main.class.getResourceAsStream("/icons/verbunden.png")));
                        connect_image = true;
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
