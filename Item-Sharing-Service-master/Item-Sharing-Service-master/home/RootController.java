package home;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RootController implements Initializable {

	@FXML Button user;
	@FXML Button manager;
	@FXML TextField getID;
	@FXML PasswordField getPW;
	@FXML TextField getPhoneNum;
	@FXML TextField getName;
	@FXML Button UserSignUp;
	@FXML Button home;
	@FXML PasswordField inputPW;
	@FXML TextField inputID;
	@FXML Button loginOK;
	
	int checkNum = -1;
	@FXML PasswordField confirmPW;
	@FXML Button submitBtn;
	@FXML Label title;
	@FXML VBox vbox;
	@FXML Label UserLogin;
	@FXML ImageView firstBackImage;
	@FXML Pane firstBackColor;
	@FXML ImageView loginBackImage;
	@FXML Pane loginBackColor;
	@FXML ImageView usersignupBackImage;
	@FXML Pane usersignupBackColor;
	@FXML ImageView managerloginBackImage;
	@FXML Pane managerloginBackColor;
	@FXML TextField inputManagerID;
	@FXML PasswordField inputManagerPW;
	@FXML Button home2;
	@FXML Button checkExistBtn;
	@FXML Button managerOK;
	@FXML Label ManagerLogin;
	@FXML Label UserSignupLabel;
	
	Socket socket;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//클라이언트 소켓 생성
		if(!MyInfo.socketConnect) {

		final String SERVER_IP = "192.168.0.83";

		final int SERVER_PORT = 8080;
		
        socket = new Socket();
        
        try {
            socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT) );
            System.out.println("success connection to server");
            MyInfo.setConnect(true);
            MyInfo.setSocket(socket);
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
	}
        
	}
	
	//user login으로 이동
	@FXML public void moveUser() throws Exception{
		Stage primaryStage = new Stage();
		Stage stage = (Stage)user.getScene().getWindow();

			Parent second = FXMLLoader.load(getClass().getResource("templates/login.fxml"));
			Scene sc = new Scene(second);
			sc.getStylesheets().add(getClass().getResource("statics/login.css").toExternalForm());
			 primaryStage.setScene(sc);
	         primaryStage.show();
			 stage.close();
	}
	
	//manager login으로 이동
	@FXML public void moveManager() throws Exception{
		Stage primaryStage = new Stage();
		Stage stage = (Stage)user.getScene().getWindow();

			Parent second = FXMLLoader.load(getClass().getResource("templates/managerLogin.fxml"));
			second.getStylesheets().add(getClass().getResource("statics/managerLogin.css").toExternalForm());
			Scene sc = new Scene(second);
			primaryStage.setScene(sc);
	         primaryStage.show();
	         primaryStage.setResizable(false);
			 stage.close();
	}
	
	//user login 버튼 이벤트
	@FXML public void UserLogin(ActionEvent event) throws Exception {
		
		socket = MyInfo.socket;
		
		MyInfo.setID(inputID.getText());
		
		//항목이 비어있는 경우 오류
		if(inputPW.getText().equals("")) {
			Alert loginFail = new Alert(AlertType.ERROR);
			loginFail.setHeaderText("Empty error");
			loginFail.setContentText("Pleas input ID or Password");
			loginFail.showAndWait();
		}
		
		//text field에 입력받은 id를 서버로 보내고, id에 해당하는 pw를 서버에서 받아온다.
		String passwordDB = null;
        try {
           String m = "LogIn:" + inputID.getText();
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
          passwordDB = br.readLine();
          
          
        } catch (IOException e1) {
           e1.printStackTrace();
        }
        //서버에서 받아온 pw를 text filed에 입력된 pw를 비교한다.
		if(passwordDB.equals(inputPW.getText())) {
				
			System.out.println("login success");
			Stage primaryStage = new Stage();
			Stage stage = (Stage)loginOK.getScene().getWindow();
			
				Parent UserPage = FXMLLoader.load(getClass().getResource("/user/templates/userMain.fxml"));
				UserPage.getStylesheets().add(getClass().getResource("/user/statics/userMain.css").toExternalForm());
				Scene sc = new Scene(UserPage);
				 primaryStage.setScene(sc);
		         primaryStage.show();
		         primaryStage.setResizable(false);
				 stage.close();
		}
		else {
		Alert loginFail = new Alert(AlertType.ERROR);
		loginFail.setHeaderText("Login Fail");
		loginFail.setContentText("login fail");
		loginFail.showAndWait();
		}
	}
	//manager login 버튼 이벤트
	@FXML public void ManagerLogin(ActionEvent event) throws Exception {
		socket = MyInfo.socket;
		
		if(inputManagerPW.getText().equals("")) {
			Alert loginFail = new Alert(AlertType.ERROR);
			loginFail.setHeaderText("Login Fail");
			loginFail.setContentText("Pleas input ID or Password");
			loginFail.showAndWait();
		}
		
		//socket login
		String passwordDB = null;
        try {
           String m = "MLogIn:" + inputManagerID.getText();
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
          passwordDB = br.readLine();
          
          
        } catch (IOException e1) {
           e1.printStackTrace();
        }
		
		if(passwordDB.equals(inputManagerPW.getText())) {
			System.out.println("login success");
			Stage primaryStage = new Stage();
			Stage stage = (Stage)managerOK.getScene().getWindow();
				Parent UserPage = FXMLLoader.load(getClass().getResource("/manager/templates/managerMain.fxml"));
				UserPage.getStylesheets().add(getClass().getResource("/manager/statics/managerMain.css").toExternalForm());
				Scene sc = new Scene(UserPage);
				 primaryStage.setScene(sc);
		         primaryStage.show();
		         primaryStage.setResizable(false);
				 stage.close();
		}
		else {
		Alert loginFail = new Alert(AlertType.ERROR);
		loginFail.setHeaderText("Login Fail");
		loginFail.setContentText("password Error");
		loginFail.showAndWait();
		}
		
	}

	
	//submit in register
	@FXML public void submitUserSignup(ActionEvent event) throws Exception {
		if(getID.getText().equals("") || getPW.getText().equals("") || getName.getText().equals("") || getPhoneNum.getText().equals("")) {
			Alert emptyError = new Alert(AlertType.ERROR);
			
			emptyError.setHeaderText("Empty Error");
			emptyError.setContentText("empty area");
			emptyError.showAndWait();
		}
		else if(checkNum != 0) {
			Alert noCheckID = new Alert(AlertType.ERROR);
			noCheckID.setHeaderText("ID Check Error");
			noCheckID.setContentText("Please ID check button");
			noCheckID.showAndWait();
		}
		else if(!getPW.getText().equals(confirmPW.getText())) {
			Alert noCheckID = new Alert(AlertType.ERROR);
			noCheckID.setHeaderText("Check Password error");
			noCheckID.setContentText("confirm password");
			noCheckID.showAndWait();
		}
		
		else {
		socket = MyInfo.socket;
		
		//회원가입 정보 서버로 전송
		String m = "Register:" + getID.getText()+":"+getPW.getText()+":"+getName.getText()+":"+getPhoneNum.getText();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
     
        pw.println(m);
        pw.flush();
        
        Alert noCheckID = new Alert(AlertType.INFORMATION);
		noCheckID.setHeaderText("Register success");
		noCheckID.setContentText("Now, you can login");
		noCheckID.showAndWait();
		
		Stage primaryStage = new Stage();
		Stage stage = (Stage)submitBtn.getScene().getWindow();

			Parent asd = FXMLLoader.load(getClass().getResource("templates/login.fxml"));
			asd.getStylesheets().add(getClass().getResource("statics/login.css").toExternalForm());
			Scene sc = new Scene(asd);
			 primaryStage.setScene(sc);
	         primaryStage.show();
	         primaryStage.setResizable(false);
			 stage.close();
		}
	}
	//ID 중복 확인
	@FXML public void checkExistID(){
		socket = MyInfo.socket;
		
		if(getID.getText().equals("")) {
			Alert noCheckID = new Alert(AlertType.ERROR);
			noCheckID.setHeaderText("ID input error");
			noCheckID.setContentText("Please input ID");
			noCheckID.showAndWait();
			return;
		}
		
        try {
           String m = "checkID:" + getID.getText();
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
          checkNum = Integer.parseInt(br.readLine());
          
          
        } catch (IOException e1) {
           e1.printStackTrace();
        }
		
		switch(checkNum) {
		case 0:
			Alert exist = new Alert(AlertType.INFORMATION);
			exist.setHeaderText("ID is available");
			exist.setContentText("ID is available");
			exist.showAndWait();
			break;
		case 1:
			Alert noExist = new Alert(AlertType.WARNING);
			noExist.setHeaderText("ID is exist");
			noExist.setContentText("id cneck again");
			noExist.showAndWait();
			break;
		}	
	}

	@FXML public void goUserSignUp() throws Exception{
		
		Stage primaryStage = new Stage();
			Parent signUp = FXMLLoader.load(getClass().getResource("templates/UserSignup.fxml"));
			signUp.getStylesheets().add(getClass().getResource("statics/UserSignup.css").toExternalForm());
			 primaryStage.setScene(new Scene(signUp));
	         primaryStage.show();
	         primaryStage.setResizable(false);
	         
	         Stage stage = (Stage)UserSignUp.getScene().getWindow();
	         stage.close();
	}


	@FXML public void goHome() throws Exception{
		Stage primaryStage = new Stage();
		Parent signUp = FXMLLoader.load(getClass().getResource("templates/first.fxml"));
		signUp.getStylesheets().add(getClass().getResource("statics/first.css").toExternalForm());
		primaryStage.setScene(new Scene(signUp));
         primaryStage.show();
         primaryStage.setResizable(false);
         
         Stage stage = (Stage)home.getScene().getWindow();
         stage.close();
	}


	@FXML public void toHome() throws Exception {
		Stage primaryStage = new Stage();
		Stage stage = (Stage)UserLogin.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/first.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/first.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}


	@FXML public void toHome2() throws Exception {
		Stage primaryStage = new Stage();
		Stage stage = (Stage)ManagerLogin.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/first.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/first.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}


	@FXML public void toHome3() throws Exception {
		Stage primaryStage = new Stage();
		Stage stage = (Stage)UserSignupLabel.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/first.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/first.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}


}
