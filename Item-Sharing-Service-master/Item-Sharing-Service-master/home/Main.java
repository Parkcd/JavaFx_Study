package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("templates/first.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("statics/first.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Item Sharing Service");
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}