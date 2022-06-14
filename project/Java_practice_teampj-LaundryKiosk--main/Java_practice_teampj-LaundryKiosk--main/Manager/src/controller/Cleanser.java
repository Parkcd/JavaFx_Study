package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MachineDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class Cleanser implements Initializable{
    @FXML
    private Label lablcname;

    @FXML
    private Label lablmnum;

    @FXML
    private Label lablprice;

    @FXML
    private Label lablmo;

    @FXML
    private Button btnaddmo;

    @FXML
    private Label lablmosen;

    @FXML
    private Button btnhome;

    @FXML
    void addmo(ActionEvent event) {
    	//db업데이트
    	if(MachineManagement.table.getMamount() < 100) {
    		boolean pass = MachineDao.machinedao.add(MachineManagement.table.getMnum(), "mamount");
    		if(pass) {
    			MachineManagement.table.setMamount(MachineManagement.table.getMamount() + 10);
    			lablmosen.setText(Integer.toString(MachineManagement.table.getMamount()) + "%");
    			lablmo.setText(Integer.toString(MachineManagement.table.getMamount()));
    		}	
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("세제가 한계량 도달");
    		alert.show();
    	} 	
    }

    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	lablcname.setText(MachineManagement.table.getCname());
    	lablmnum.setText(Integer.toString(MachineManagement.table.getMnum()));
    	lablprice.setText(Integer.toString(MachineManagement.table.getWholeprice()));
    	lablmo.setText(Integer.toString(MachineManagement.table.getMamount()));
    	lablmosen.setText(Integer.toString(MachineManagement.table.getMamount()) + "%");
    }
}
