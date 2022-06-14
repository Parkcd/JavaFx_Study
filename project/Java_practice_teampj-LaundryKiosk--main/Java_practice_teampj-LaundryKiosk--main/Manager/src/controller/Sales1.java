package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.SalesDao;
import dto.count;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class Sales1 implements Initializable{
    @FXML
    private Button btnch2;

    @FXML
    private Button btn;

    @FXML
    private AreaChart chart;

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
    	ArrayList<count> count2 = SalesDao.salesDao.frequency();
    	int p1 = 0;
    	int p2 = 0;
    	int p3 = 0;
    	int p4 = 0;
    	for(count temp : count2) {
    		if(temp.getCname().contains("중형세탁기")) {
    			p1++;
    		}else if(temp.getCname().contains("대형세탁기")) {
    			p2++;
    		}else if(temp.getCname().contains("중형건조기")) {
    			p3++;
    		}else if(temp.getCname().contains("대형세탁기")) {
    			p4++;
    		}
    	}
    	XYChart.Series case1 = new XYChart.Series();
		case1.setName("세탁/건조이용빈도");
		case1.setData(FXCollections.observableArrayList(
				new XYChart.Data("중형세탁기", p1),
				new XYChart.Data("대형세탁기", p2),
				new XYChart.Data("중형건조기", p3),
				new XYChart.Data("대형건조기", p4)
				));
		chart.getData().add(case1);
    }
}
