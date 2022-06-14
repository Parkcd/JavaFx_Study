package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.mail.internet.NewsAddress;

import dao.SalesDao;
import dto.date;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class Sales2 implements Initializable{

    @FXML
    private Button btnsales1;

    @FXML
    private Button btnhome;

    @FXML
    private AreaChart chart;

    @FXML
    void home(ActionEvent event) {
    	Main.main.loadpage("/view/관리자1페이지.fxml");
    }

    @FXML
    void sales1(ActionEvent event) {
    	Main.main.loadpage("/view/관리자7페이지(이용빈도).fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ArrayList<date> date2 = SalesDao.salesDao.frequency2();
    	XYChart.Series<String, Integer> series = new XYChart.Series<>();
    	for(date temp : date2) {
    		series.getData().add(new XYChart.Data<>(temp.getDate(),temp.getCount()));
    	}
    	
    	chart.getData().add(series);
    }
}
