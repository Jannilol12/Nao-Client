package tes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class tester {
	public static void load() {
		Stage stage = new Stage();
		Button button = new Button();

		button.setOnMousePressed(event -> {
			System.out.println("pressed");
		});
		
		Scene scene = new Scene(button);
		stage.setScene(scene);
		stage.show();
	}
}
