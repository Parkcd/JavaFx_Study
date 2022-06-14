package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CPage {
	public void CreatePage(Stage stage, String FXMLRout) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(FXMLRout));
		stage.setScene(new Scene(root));
		stage.show();
	}// 2020 12 07 ±èÅÂÇå
}
