package user;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.control.TableView;

public class likeController {

	@FXML Button likemoveMypage_Btn;
	@FXML Label likeMoveUserHome;
	@FXML HBox likeBtnColor;
	@FXML Button likeBtn1;
	@FXML Button likeBtn2;
	@FXML Button likeBtn3;
	@FXML Button likeBtn4;
	@FXML Button likeBtn5;
	@FXML Button likeBtn6;
	@FXML Label likeTitle;
	@FXML HBox likeTopHbox;
	@FXML AnchorPane likeBackColor;
	@FXML Line likeLine1;
	@FXML Label likeLabel;
	@FXML Line likeLine2;
	@FXML Button likeEraseBtn;
	@FXML Button likeNoticeBtn;
	@FXML TableView likeTableView;

	@FXML public void likemoveMyPage() throws Exception{
		Stage primaryStage = new Stage();
		Stage stage = (Stage)likeTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/myPage.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/myPage.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
			stage.close();
	}

	@FXML public void likeMoveUserHome() throws Exception{
		Stage primaryStage = new Stage();
		Stage stage = (Stage)likeTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/userMain.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/userMain.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
			stage.close();
	}

	@FXML public void likeLink() {
		Hyperlink myHyperlink = new Hyperlink();
		myHyperlink.setText("test");
		try {
			Desktop.getDesktop().browse(new URI("https://enjoyso.tistory.com/62"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
