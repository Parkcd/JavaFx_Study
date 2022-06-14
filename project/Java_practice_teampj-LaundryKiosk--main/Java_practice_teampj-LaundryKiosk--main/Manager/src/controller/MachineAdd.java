package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.CategoryDao;
import dao.MachineDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MachineAdd implements Initializable{

    @FXML
    private Label money;

    @FXML
    private Button btnup;

    @FXML
    private Button btndown;

    @FXML
    private Button btncheck;

    @FXML
    private Button btnhome;

    @FXML
    private TextField nametext;

    @FXML
    void check(ActionEvent event) {
    	//db 추가
    	if(nametext.getText().equals("중형세탁기") || nametext.getText().equals("대형세탁기") || nametext.getText().equals("중형건조기") ||nametext.getText().equals("대형건조기")) {
    		int mnum = 0;
    		if(nametext.getText().contains("건조기")) {
    			mnum = MachineDao.machinedao.insert("null");
    		}else {
    			//머신 추가
        		mnum = MachineDao.machinedao.insert("100");
    		}	
    		//카테고리 추가
    		boolean pass = CategoryDao.categoryDao.insert(mnum, nametext.getText(), Integer.parseInt(money.getText()));
    		if(pass) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("등록성공 기계번호:" + mnum);
    			alert.show();
    			//관리자4페이지 로드
            	Main.main.loadpage("/view/관리자2페이지.fxml");
    		}
    		
    	}else {
    		System.out.println("no");
    	}
    	
    }

    @FXML
    void down(ActionEvent event) {
    	int moneytemp = Integer.parseInt(money.getText());
    	if(moneytemp != 0) {
    		moneytemp -= 500;
    		money.setText(Integer.toString(moneytemp));
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("0이하는 가격 내릴수없습니다.");
    		alert.show();
    	}
    	//Main.main.loadpage("/view/관리자3페이지추가.fxml");
    }

    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }

    @FXML
    void up(ActionEvent event) {
    	int moneytemp = Integer.parseInt(money.getText());
    		moneytemp += 500;
    		money.setText(Integer.toString(moneytemp)); 	
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }
}
