
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFrame extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = null;

		stage.setOnCloseRequest(event -> {
			System.exit(0);
		});

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainFrame.class.getResource("/Display.fxml"));
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		try {
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void launching() {
		new Thread(() -> {
			MainFrame.launch(new String[] {});
		}).start();
	}
}