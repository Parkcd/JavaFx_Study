package controller;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private Label IblStatus;
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	public void Login(ActionEvent event) throws Exception{
		if(txtUserName.getText().equals("user") && txtPassword.getText().equals("pass")) {
			IblStatus.setText("로그인 성공");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ComMainView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} else {
			IblStatus.setText("로그인 실패");
		}
	}
}
