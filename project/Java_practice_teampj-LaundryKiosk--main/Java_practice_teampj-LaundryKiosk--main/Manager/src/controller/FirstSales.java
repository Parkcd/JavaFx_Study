package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class FirstSales  implements Initializable{
	@FXML
    private Button btnch1;

    @FXML
    private Button btnch2;

    @FXML
    private Button btnhome;

    @FXML
    void ch1(ActionEvent event) {
    	Main.main.loadpage("/view/관리자7페이지(이용빈도).fxml");
    }

    @FXML
    void ch2(ActionEvent event) {
    	Main.main.loadpage("/view/관리자7페이지(수익).fxml");
    }

    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }
}
