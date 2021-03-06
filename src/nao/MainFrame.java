package nao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * start the Application with JavaFX
 */
public class MainFrame extends Application{
	public static Stage stage;

	/**
	 * Start the window
	 * @param stage getting the "window" (needed for javaFX)
	 * @throws Exception throw exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Scene scene = null;

		//set icon
		stage.getIcons().add(new Image(MainFrame.class.getResourceAsStream("/icons/JJ.png")));

		//if window is closed, close everything
		stage.setOnCloseRequest(event -> {
			System.exit(0);
		});

		//load the main fxml -> Display.fxml
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainFrame.class.getResource("/nao/fxml/Display.fxml"));
		try {
			//set everything up
			scene = new Scene(loader.load());
			stage.setScene(scene);
			//show the window
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * close the window
	 */
	@Override
	public void stop() {
		try {
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * launch the window
	 */
	public static void launching() {
		new Thread(() -> {
			MainFrame.launch(new String[] {});
		}).start();
	}
}