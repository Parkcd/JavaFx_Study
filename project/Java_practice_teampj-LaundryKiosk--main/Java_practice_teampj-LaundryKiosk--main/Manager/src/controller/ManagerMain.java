package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ManagerMain implements Initializable{
	  @FXML
	  private Button btnmanagement;

	  @FXML
	  private Button btnsalesview;

	  @FXML
	  void management(ActionEvent event) {
		  controller.Main.main.loadpage("/view/관리자2페이지.fxml");
	  }

	  @FXML
	  void salesview(ActionEvent event) {
		  controller.Main.main.loadpage("/view/관리자7페이지(초기화면).fxml");
	  }
	  @Override
	  public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	  }
}
