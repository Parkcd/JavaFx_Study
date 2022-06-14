package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.MachineDao;
import dao.SalesDao;
import dto.Machine;
import dto.temptable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MachineManagement implements Initializable{
    @FXML
    private TableView<temptable> mtableview;

    @FXML
    private Button btnmadd;

    @FXML
    private Button btnhome;
    
    public static temptable table;
    
    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }

    @FXML
    void madd(ActionEvent event) {
    	Main.main.loadpage("/view/관리자3페이지추가.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ArrayList<Machine> mlist = MachineDao.machinedao.list();
    	ObservableList<temptable> tlist = FXCollections.observableArrayList();
    	for(Machine temp : mlist) {
    		tlist.addAll(SalesDao.salesDao.tlist(temp));
    	}
    	//테이블뷰 넣기
    	TableColumn tc = mtableview.getColumns().get(0);	// 테이블에서 첫번째 열 가져오기 
    	tc.setCellValueFactory( new PropertyValueFactory<>("cname")); // 
    	//tc.setCellValueFactory( new PropertyValueFactory<>("리스트내 자료형의 표시할 필드명"));
    	tc = mtableview.getColumns().get(1);	// 테이블에서 두번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("mnum"));
    	
    	tc = mtableview.getColumns().get(2);	// 테이블에서 세번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("mamount"));
    	
    	tc = mtableview.getColumns().get(3);	// 테이블에서 네번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("wholeprice"));
    	
    	mtableview.setItems(tlist);
    	mtableview.setOnMouseClicked( e -> {
    		table = mtableview.getSelectionModel().getSelectedItem();
    		
    		Main.main.loadpage("/view/관리자4페이지.fxml");
    	});
    }
}
