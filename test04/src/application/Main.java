package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// stage > scene > container > node
			
			Label text = new Label();
			text.setText("Hello JavaFX"); // Hello JavaFX 출력
			//CSS (나중에는 이렇게 일일이 안하고 외부파일로 처리해서 사용함)
			//text.setStyle("-fx-font-size: 80"); // font size 키우기 
			//text.setStyle("-fx-font-size:50;-fx-text-fill:red"); // font size 키우기 and 글자 색 바꾸기
			//text.setStyle("-fx-font-size:50;-fx-background-color:yellow"); // font size 키우기 뒷배경 색상 바꾸기
			text.setStyle("-fx-font-size:50;-fx-border-color:green"); // font size 키우기 뒷배경 색상 바꾸기
			
			
			StackPane pane = new StackPane();
			pane.getChildren().add(text); 
			
			Scene scene = new Scene(pane , 500 , 300);
			
			stage.setScene(scene);
			
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
