package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CSingelton;
import model.DBConnect;

public class LoginController {

    @FXML
    private AnchorPane anPane;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXPasswordField tfPass;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnLogin;

    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void createLogin(ActionEvent event) throws SQLException, IOException {
        CSingelton mov = CSingelton.getInstance();
    	//System.out.println("로그인 페이지로");
    	/*if(tfEmail.getText().equals("")) {
    		mgs.setMessage("이름 미입력!");    	}*/
    	//DB에 이메일과 비밀번호를 확인하여 로그인 가능 또는 불가
    	String sql = "SELECT*FROM userdata WHERE id=? AND password=?";
    	pstmt = mov.getDBConnect().prepareStatement(sql);
    	pstmt.setNString(1,  tfEmail.getText());
    	pstmt.setNString(2,  tfPass.getText());

    	rs = pstmt.executeQuery(); //execute 쿼리는 겱과값이 있고 update는 없다.

        boolean LoginSucess = false;
    	if(rs.next()) {
    		//msg.setMessage("Success!");
    		//현재 창을 닫는다.
    		if(tfEmail.getText().equals(rs.getNString("id")) &&
    				tfPass.getText().equals(rs.getNString("PASSWORD"))) {
    			if(rs.getNString("permit").equals("관리자")) {
    				mov.setManagerOrViewer(1);
    			}
        		LoginSucess = true;
    		}
    		if(LoginSucess == true) {
        		btnLogin.getScene().getWindow().hide();
        		//로그인 성공 후 홈페이지열기
        		Stage home = new Stage();
            	Parent root = FXMLLoader.load(getClass().getResource("../view/Main_KimtaeHeon.fxml"));
        		Scene scene = new Scene(root);
        		home.setScene(scene);
        		home.show();
        		msg.setMessage("Success!");
    		}
    	}
    	else {
    		msg.setMessage("아이디와 비밀번호가 잘못되었습니다.");

    	}
    }

    @FXML
    void createregister(ActionEvent event) throws IOException {
    		//System.out.println("가입 페이지로");
    	btnRegister.getScene().getWindow().hide(); //현재 페이지를 안보이게

    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
    }

}
