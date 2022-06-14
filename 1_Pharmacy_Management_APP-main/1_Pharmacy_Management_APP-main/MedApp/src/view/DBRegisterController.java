package view;

import java.sql.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.CSingelton;
import model.DBConnect;

public class DBRegisterController implements Initializable{
	@FXML
	private Label ManagerOrViewer;

	@FXML
	private AnchorPane anPane;
	@FXML
    private JFXTextField name;
	@FXML
    private JFXTextField character;
	@FXML
    private JFXTextField effect;
	@FXML
    private JFXTextField warning;
	@FXML
    private JFXTextField company;
	@FXML
    private JFXDatePicker expiration;
	@FXML
    private JFXTextField price;
	@FXML
    private JFXTextField stock;
	@FXML
    private JFXButton btnRegister;
	@FXML
    private JFXButton btnHome;
	@FXML
    private Button btnFChooser;
	@FXML
	private ImageView imgView;

    @FXML
    private Label mov;

    String path = DBRegisterController.class.getResource("").getPath();
    // 현재 클래스의 절대 경로를 가져온다. // 2020 12 11 김태헌
	Message msg = new Message();
	CSingelton MOrV = CSingelton.getInstance();

    @FXML
    void Exit(ActionEvent event) {
    	btnFChooser.getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(MOrV.getManagerOrViewer() == 1) {
			mov.setText("로그인 정보 : 관리자");
		} else {
			mov.setText("로그인 정보 : 열람자");
		}
	}
	public void fileChoose() {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("");
		fChooser.setInitialDirectory(new File(path));
		ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
		fChooser.getExtensionFilters().add(imgType);
		ExtensionFilter txtType = new ExtensionFilter("text file", "*.txt", "*.doc");
		fChooser.getExtensionFilters().addAll(imgType, txtType);

		File selectedFile = fChooser.showOpenDialog(null);

		path = initImage(selectedFile);

		System.out.println(selectedFile);
		try {
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			Image img = new Image(bis);
			imgView.setImage(img);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//protected long saveBitmap(SQLiteDatabase database, Bitmap bmp)

	 @FXML
	    void register(ActionEvent event) throws SQLException{
	    		//DB로 입력내용을 저장->Insert into
	    	if(MOrV.getManagerOrViewer() == 1) {
	    		//권한이 있는 경우
	    		String sql = "INSERT INTO medicine Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    		PreparedStatement pstmt = MOrV.getDBConnect().prepareStatement(sql);
	    		pstmt.setNString(1,  name.getText());
	    		pstmt.setNString(2,  path);
	    		pstmt.setNString(3,  character.getText());
	    		pstmt.setNString(4,  effect.getText());
	    		pstmt.setNString(5,  warning.getText());
	    		pstmt.setNString(6,  company.getText());
	    		pstmt.setDate(7, Date.valueOf(expiration.getValue()));
	    		pstmt.setLong(8,  Integer.parseInt(price.getText()));
	    		pstmt.setLong(9,  Integer.parseInt(stock.getText()));

	    		pstmt.executeUpdate();

	    		msg.setMessage("입력성공");

	    	}
	    	else {//권한이 없는 경우
	    		msg.setMessage("등록 권한이 없습니다.");
	    	}
	    }
	 /*이미지가져오기*/
	 public class CustomImage{
		 private ImageView image;
		 CustomImage(ImageView img){
			 this.image = img;
		 }
		 public void setImage(ImageView value) {
			 image = value;
		 }
		 public ImageView getImage() {
			 return image;
		 }
	 }
	 private String initImage(File imageFile) {
		 String str = imageFile.toURI().toString(); //resource 폴더에서 불러오는 방법
		 return str;
	 }
}


