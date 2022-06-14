package user;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import home.MyInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;

public class addItemController implements Initializable {

	@FXML TextArea contents;
	@FXML Button moveMypage_Btn;
	@FXML TextField price;
	@FXML DatePicker limit_date;
	@FXML ComboBox<String> kinds;
	@FXML TextField post_title;
	@FXML Label aiMoveUserHome;
	@FXML HBox aiBtnColor;
	@FXML Button aiBtn1;
	@FXML Button aiBtn2;
	@FXML Button aiBtn3;
	@FXML Button aiBtn4;
	@FXML Button aiBtn5;
	@FXML Button aiBtn6;

	Socket socket = null;
	@FXML Label aiTitle;
	@FXML AnchorPane aiBackColor;
	@FXML HBox aiTopHbox;
	@FXML Button aiMoveUserHomeBtn;
	@FXML Button aiPostSubmitBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//combo Box 초기화
		ObservableList<String> kindsList = FXCollections.observableArrayList("기내용품","캐리어", "카메라장비", "생필품", "기타");
		kinds.setItems(kindsList);
	}
	
	//item 등록 버튼 이벤트
	@FXML public void postSubmit(ActionEvent event) throws Exception {
		
		LocalDate localDate = limit_date.getValue();
		
		String name = post_title.getText();
		String kind = kinds.getValue();
		String date = localDate.toString();
		String perDayPrice = price.getText();
		String content = contents.getText();
		String postByID = home.MyInfo.my_id;
		
		if(name == null || kind == null || date == null || perDayPrice == null || content == null) {
			Alert emptyError = new Alert(AlertType.ERROR);
			emptyError.setHeaderText("Empty error");
			emptyError.setContentText("빈 항목을 작성해주세요.");
			emptyError.showAndWait();
		}
		else {
			socket = MyInfo.socket;
			
	        try {
	        	//사용자가 작성한 정보 서버로 전송
	           String m = "addItem:" + name+":"+kind+":"+postByID+":"+content+":"+perDayPrice+":"+date;
	           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	           
	           pw.println(m);
	           pw.flush();
	          
	        } catch (IOException e1) {
	           e1.printStackTrace();
	        }
			
			Alert addNotification = new Alert(AlertType.INFORMATION);
			addNotification.setHeaderText("Success");
			addNotification.setContentText("등록에 성공하였습니다.");
			addNotification.showAndWait();
			
			Stage primaryStage = new Stage();
			Stage stage = (Stage)post_title.getScene().getWindow();

				Parent ob = FXMLLoader.load(getClass().getResource("templates/userMain.fxml"));
				ob.getStylesheets().add(getClass().getResource("statics/userMain.css").toExternalForm());
				Scene sc = new Scene(ob);
				primaryStage.setScene(sc);
		        primaryStage.show();
		        primaryStage.setResizable(false);
				stage.close();
		}
	}

	@FXML public void moveUserHome() throws Exception {
		
		Stage primaryStage = new Stage();
		Stage stage = (Stage)post_title.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/userMain.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/userMain.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}
	
	//공지사항 하이퍼링크
	@FXML public void aiLink() {
		Hyperlink myHyperlink = new Hyperlink();
		myHyperlink.setText("test");
		try {
			Desktop.getDesktop().browse(new URI("https://enjoyso.tistory.com/62"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
