package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			root.setPrefWidth(350);
			root.setPrefHeight(150);
			root.setAlignment(Pos.CENTER);
			root.setSpacing(20);
			
			Label label = new Label();
			label.setText("Hello, JavaFX");
			label.setFont(new Font(50));
			
			Button button = new Button();
			button.setText("확인");
			button.setOnAction(event->Platform.exit());
			
			//root.getChildren().add(label);
			//root.getChildren().add(button);
			root.getChildren().addAll(label , button);
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("AppMain");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
