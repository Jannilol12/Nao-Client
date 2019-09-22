package nao.controllers;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import components.json.JSONArray;
import components.json.JSONObject;
import components.json.finder.JSONFinder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import nao.SendMessages;
import nao.sender;

public class controller_main implements Initializable {
	private SendMessages b = new SendMessages();
    
    @FXML
    private ListView<Zwischenspeicher> list;

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
    void connect(ActionEvent event) {
        try {
            sender.connected(ip.getText(), Integer.parseInt(port.getText()));
            b.sendBattery();
            b.sendVolume();
            b.sendFileRequest();
            b.sendTemperature();
        }
        catch(Exception err) {}
    }

    @FXML
    void destroy(ActionEvent event) {
        sender.destroy();
        b.stopBattery();
        b.stopTemperature();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmain = this;

        list.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Zwischenspeicher prog = list.getSelectionModel().getSelectedItem();
                if(prog != null) {
                	JSONArray inputArray = prog.getInputArgs();
                	
                	if(inputArray == null)
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
        	            GridPane gridPane = new GridPane();
        	            gridPane.setHgap(10);
        	            gridPane.setVgap(10);
        	            
        	            List<Node> inputNodes = new LinkedList<>();
        	            
        	            for(Object obj : inputArray.getList()) {
        	            	if(obj == null) continue;
        	            	
        	            	if(obj instanceof JSONObject) {
        	            		JSONObject input = (JSONObject) obj;
        	            		
        	            		String id = JSONFinder.getString("id", input);
        	            		String type = JSONFinder.getString("type", input);
        	            		if(type == null || id == null) continue;
        	            		String prompt = JSONFinder.getString("prompt", input);
        	            		
        	            		Node currentNode = null;
        	            		
        	            		if(type.equalsIgnoreCase("text")) {
        	            			TextField textField = new TextField();
        	            			if(prompt != null)
        	            				textField.setPromptText(prompt);
        	            			
        	            			currentNode = textField;
        	            		} else if(type.equalsIgnoreCase("color")) {
        	            			ColorPicker colorPicker = new ColorPicker();
        	            			if(prompt != null)
        	            				colorPicker.setPromptText(prompt);
        	            			
        	            			currentNode = colorPicker;
        	            		} else if(type.equalsIgnoreCase("boolean")) {
        	            			CheckBox checkBox = new CheckBox("");
        	            			currentNode = checkBox;
        	            			
        	            		} else if(type.equalsIgnoreCase("int")) {
        	            			int min = JSONFinder.getInt("min", input);
        	            			int max = JSONFinder.getInt("max", input);
        	            			if(max < min)max = min + 1;
        	            			int def = JSONFinder.getInt("def", input);
        	            			if(def < min || def > max) def = min;
        	            			
        	            			Spinner<Integer> spinner = new Spinner<>();
        	            			SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, def);
        	            			spinner.setValueFactory(spinnerValueFactory);
        	            			currentNode = spinner;
        	            		} else if(type.equalsIgnoreCase("double")) {
        	            			double min = JSONFinder.getDouble("min", input);
        	            			double max = JSONFinder.getDouble("max", input);
        	            			if(max < min)max = min + 1;
        	            			double def = JSONFinder.getDouble("def", input);
        	            			if(def < min || def > max) def = min;
        	            			
        	            			Spinner<Double> spinner = new Spinner<>();
        	            			SpinnerValueFactory<Double> spinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max, def);
        	            			spinner.setValueFactory(spinnerValueFactory);
        	            			currentNode = spinner;
        	            			
        	            		} else //Add here more
        	            			continue;
        	            		
        	            		currentNode.setId(id);
        	            		inputNodes.add(currentNode);
        	            		
        	            		int row = gridPane.getRowCount();
        	            		gridPane.add(new Label(id + ":"), 0, row);
        	            		gridPane.add(currentNode, 1, row);
        	            	}
        	            }
        	            
        	            root.setContent(gridPane);
        	            root.setMinWidth(500);
        	            
        	            Optional<ButtonType> result = alert.showAndWait();
        	            if(result.get() == ButtonType.OK) {
        	            	List<JSONObject> outObjects = new LinkedList<>();
        	            	JSONArray outArray = new JSONArray(outObjects);
        	            	
        	            	for(Node node : inputNodes) {
        	            		JSONObject outObject = new JSONObject();
        	            		
        	            		if(node instanceof TextField) {
        	            			outObject.add("value", ((TextField) node).getText());
        	            		} else if(node instanceof ColorPicker) {
        	            			outObject.add("value", ((ColorPicker) node).getValue().toString());
        	            		} else if(node instanceof CheckBox) {
        	            			outObject.add("value", ((CheckBox) node).isSelected());
        	            		} else if(node instanceof Spinner<?>) {
        	            			outObject.add("value", ((Spinner<?>) node).getValue());
        	            		}
        	            		//Add here more
        	            		
        	            		outObject.add("id", node.getId());
        	            		outObjects.add(outObject);
        	            	}
        	            	
        	            	JSONObject sendOut = new JSONObject();
        	            	sendOut.add("type", "RunP");
        	            	sendOut.add("value", prog.name);
        	            	sendOut.add("inputs", outArray);
        	            	System.out.print(sendOut.toJSONString());
        	            	sender.sendMessage(sendOut.toJSONString());
        	            }
        	            
                	}
                }
            }
        });

        new Thread( () -> {
            while(true) {
                if (sender.isClosed()) {
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
