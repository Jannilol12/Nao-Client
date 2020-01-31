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

/**
 * Main Controller from the Client
 */
public class MainController implements Initializable {
    
    @FXML
    private ListView<CachForPrograms> list;

    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    @FXML
    private ImageView connected;
    private boolean connect_image;

    public static MainController cmain;

	/**
	 * Clears the list of the programs on the left side of the client
	 */
	public void clearProgs() {
		//if Platform is doing nothing -> Clear list
        if(Platform.isFxApplicationThread()) {
			list.getItems().clear();
		//else do it later
		}else {
			Platform.runLater(() -> {
				list.getItems().clear();
			});
		}
    }

	/**
	 * Add a program to the list on the left side of the client
	 * @param prog programm, which should be added
	 */
	public void addProg(CachForPrograms prog){
		//if Platform is doing nothing -> add
        if(Platform.isFxApplicationThread()) {
			list.getItems().add(prog);
		//else do it later
		}else {
			Platform.runLater(() -> {
				list.getItems().add(prog);
			});
		}
    }

	/**
	 * try to connect to the server
	 * @param event never used
	 */
	@FXML
    void connect(ActionEvent event) {
        try {
        	//connect with the ip and port
            sender.connected(ip.getText(), Integer.parseInt(port.getText()));

            //initialize everything which has to be initialized
            SendMessages.sendBattery();
            SendMessages.sendAudioPlayerVolume();
            SendMessages.sendAudioFiles();
            SendMessages.sendTemperature();
            SendMessages.sendVocabulary();
            SendMessages.sendFaceNames();
            SendMessages.sendBehavior();
            SendMessages.sendAllFiles();
            SendMessages.sendListMethods();
        }
        catch(Exception err) {}
    }

	/**
	 * disconnect from the server
	 * @param event never used
	 */
	@FXML
    void destroy(ActionEvent event) {
        sender.destroy();
        //this messages are sent many times, so they also have to be stopped
        SendMessages.stopBattery();
        SendMessages.stopTemperature();
        //if disconnect when playing an audio, also stop sending messages
        SendMessages.stopAudioPlayerPositionOfFile();
    }

	/**
	 * Initialize is like a constructor for JavaFX
	 * @param url            never used
	 * @param resourceBundle never used
	 */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmain = this;

        //Listener if one of the program in the list on the left side is clicked
        list.setOnMouseClicked(event -> {
        	//if double click
            if(event.getClickCount() == 2){
                CachForPrograms prog = list.getSelectionModel().getSelectedItem();
                //if program which is clicked is a real program and not something different
                if(prog != null) {
                	JSONArray inputArray = prog.getInputArgs();

                	//if the program need inputs, for example "Say" needs a String which shall be said
					//if null, just run the program, otherwise we need a window for the input
                	if(inputArray == null) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.add("type", "RunP");
						jsonObject.add("value", prog.name);
						sender.sendMessage(jsonObject.toJSONString());
					}else {
                		//alert window for the input
                		Alert alert = new Alert(AlertType.CONFIRMATION);
        	            DialogPane root = alert.getDialogPane();

        	            //if one button in the Alert Window is clicked -> close it
        	            for (ButtonType buttonType : root.getButtonTypes()) {
        	                ButtonBase button = (ButtonBase) root.lookupButton(buttonType);
        	                button.setOnAction(evt -> {
        	                    alert.close();
        	                });
        	            }
        	            alert.setHeaderText("Arguments Required");

        	            //design of the window
        	            GridPane gridPane = new GridPane();
        	            gridPane.setHgap(10);
        	            gridPane.setVgap(10);
        	            
        	            List<Node> inputNodes = new LinkedList<>();

        	            //for each input, a TextField, ColorPicker, CheckBox or what ever, is needed
        	            for(Object obj : inputArray.getList()) {
        	            	//if input needs nothing -> continue
        	            	if(obj == null) continue;

        	            	//information about the needed inputs are saved as a JSONObject
        	            	if(obj instanceof JSONObject) {
        	            		JSONObject input = (JSONObject) obj;

        	            		//id -> name what is needed
								//type -> text, color, boolean, int, double -> for more, add and else if below
        	            		String id = JSONFinder.getString("id", input);
        	            		String type = JSONFinder.getString("type", input);

        	            		//if nothing set -> continue
        	            		if(type == null || id == null) continue;
        	            		String prompt = JSONFinder.getString("prompt", input);
        	            		
        	            		Node currentNode = null;

        	            		//if text is needed -> create a TextField
        	            		if(type.equalsIgnoreCase("text")) {
        	            			TextField textField = new TextField();
        	            			if(prompt != null)
        	            				textField.setPromptText(prompt);
        	            			
        	            			currentNode = textField;

        	            		//if color is needed -> ColorPicker
        	            		} else if(type.equalsIgnoreCase("color")) {
        	            			ColorPicker colorPicker = new ColorPicker();
        	            			if(prompt != null)
        	            				colorPicker.setPromptText(prompt);
        	            			
        	            			currentNode = colorPicker;

        	            		//if boolean is needed -> CheckBox
        	            		} else if(type.equalsIgnoreCase("boolean")) {
        	            			CheckBox checkBox = new CheckBox("");
        	            			currentNode = checkBox;

        	            		//if int is needed -> Spinner where you can select from the min to the max int
								//min and max is given from the Server
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
								//if double is needed -> Spinner where you can select from the min to the max int
								//min and max is given from the Server
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
        	            			
        	            		} else { //Add here more
									continue;
								}
        	            		
        	            		currentNode.setId(id);
        	            		inputNodes.add(currentNode);

        	            		//add all inputs below each other
        	            		int row = gridPane.getRowCount();
        	            		gridPane.add(new Label(id + ":"), 0, row);
        	            		gridPane.add(currentNode, 1, row);
        	            	}
        	            }

        	            //set everything up for the window
        	            root.setContent(gridPane);
        	            root.setMinWidth(500);
        	            
        	            Optional<ButtonType> result = alert.showAndWait();

        	            //if OK Button is clicked -> send all the information to the nao
        	            if(result.get() == ButtonType.OK) {
        	            	List<JSONObject> outObjects = new LinkedList<>();
        	            	JSONArray outArray = new JSONArray(outObjects);

        	            	//for every input which is given
        	            	for(Node node : inputNodes) {
        	            		JSONObject outObject = new JSONObject();

        	            		//add the information of the TextField, ColorPicker, CheckBox, Spinner or what ever you add
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

        	            	//send the information to the Server
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

        //look every second if the client is connected to the server and change the picture, if connection has been closed/open
        new Thread( () -> {
            while(true) {
            	//if connection is closed
                if (sender.isClosed()) {
                	//if image shows connected -> change image to disconnected
                    if(connect_image != false) {
                        connected.setImage(new Image(MainController.class.getResourceAsStream("/icons/getrennt.png")));
                        connect_image = false;
                    }
                //else -> still connected
                } else {
                	//if image shows disconnected -> change image to connected
                    if(!connect_image) {
                        connected.setImage(new Image(MainController.class.getResourceAsStream("/icons/verbunden.png")));
                        connect_image = true;
                    }
                }

                //wait 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
