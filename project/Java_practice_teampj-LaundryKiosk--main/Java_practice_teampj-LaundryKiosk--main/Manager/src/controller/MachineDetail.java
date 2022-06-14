package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MachineDetail implements Initializable{
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
    private Button btnreprice;

    @FXML
    private Button btnhome;

    @FXML
    void addmo(ActionEvent event) {
    	Main.main.loadpage("/view/관리자5페이지.fxml");
    }

    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }

    @FXML
    void reprice(ActionEvent event) {
    	Main.main.loadpage("/view/관리자6페이지.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	lablcname.setText(MachineManagement.table.getCname());
    	lablmnum.setText(Integer.toString(MachineManagement.table.getMnum()));
    	lablprice.setText(Integer.toString(MachineManagement.table.getWholeprice()));
    	lablmo.setText(Integer.toString(MachineManagement.table.getMamount()));
    }
}
