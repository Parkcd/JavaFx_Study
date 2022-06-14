package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.CategoryDao;
import dao.MachineDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class Price implements Initializable{
	@FXML
    private Label lablcname;

    @FXML
    private Label lablmnum;

    @FXML
    private Label lablprice;

    @FXML
    private Label lablmo;

    @FXML
    private Button btnup;

    @FXML
    private Button btndown;

    @FXML
    private Button btncheck;

    @FXML
    private Button btnhome;

    @FXML
    void check(ActionEvent event) {
    	boolean pass = CategoryDao.categoryDao.up(MachineManagement.table.getMnum(), MachineManagement.table.getWholeprice());
    	if(pass) {
    		Main.main.loadpage("/view/관리자1페이지.fxml");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("가격조절 성공");
    		alert.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("가격조절시스템 에러 [관리자 문의]");
    		alert.show();
    	}
    	
    }

    @FXML
    void down(ActionEvent event) {
    	if(MachineManagement.table.getWholeprice() <= 0) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("현재가격이 0원 입니다!");
    		alert.show();
    	}else {
    		MachineManagement.table.setWholeprice(MachineManagement.table.getWholeprice() - 500); 			
    		lablprice.setText(Integer.toString(MachineManagement.table.getWholeprice()));
    	}
    }

    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }

    @FXML
    void up(ActionEvent event) {
    	MachineManagement.table.setWholeprice(MachineManagement.table.getWholeprice() + 500); 			
    	lablprice.setText(Integer.toString(MachineManagement.table.getWholeprice()));
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	lablcname.setText(MachineManagement.table.getCname());
    	lablmnum.setText(Integer.toString(MachineManagement.table.getMnum()));
    	lablprice.setText(Integer.toString(MachineManagement.table.getWholeprice()));
    	lablmo.setText(Integer.toString(MachineManagement.table.getMamount()));
    	
    }
}
