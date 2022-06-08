package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Label label = new Label();
			label.setText(Integer.toString(num));
			
			label.setStyle("-fx-font-size:50");
			
			Button button = new Button();
			button.setText("Add");
			button.setStyle("-fx-font-size:15");
			button.setOnAction(e ->{  // 객체를 함수로 만들때 람다식 사용
				num++;
				label.setText(Integer.toString(num));
			});

			
			
			StackPane pane = new StackPane();
			//pane.getChildren().add(label);
			//pane.getChildren().add(button);
			pane.getChildren().addAll(label , button);
			StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
			
			
			Scene scene = new Scene(pane , 150 ,150);
			
			stage.setScene(scene);
			stage.setTitle("Add Program");
			//stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	
	int num = 0; // 전역변수
	public static void main(String[] args) {
		launch(args);
	}
}
