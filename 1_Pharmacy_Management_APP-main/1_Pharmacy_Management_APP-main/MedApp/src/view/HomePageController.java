package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.cell.PropertyValueFactory;
import model.Team01;
import model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import model.CSingelton;
import model.Medicine;

public class HomePageController implements Initializable {
	@FXML
	private JFXTextField email;

	@FXML
	private JFXTextField password;

	@FXML
	private JFXTextField fullname;

	@FXML
	private JFXTextField host;

	@FXML
	private TableView<UserData> tvTeam01;
	@FXML
	private TableColumn<UserData, String> colEmail;
	@FXML
	private TableColumn<UserData, String> colPass;
	@FXML
	private TableColumn<UserData, String> colFname;
	@FXML
	private TableColumn<UserData, String> colHost;

	@FXML
	private Button btnInsert;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;

	CSingelton Singelton = CSingelton.getInstance();

	// 버튼을 눌렀을때 ( 3개 인서트, 업데이트, 딜레이트 )
	@FXML
	public void handelButton(ActionEvent event) {
		// System.out.println("버튼을 눌렀음!");
		// showBooks();
		if (event.getSource() == btnInsert) { // btnInsert 버튼을 눌렀을때!!!
			insertRow();
		} else if (event.getSource() == btnUpdate) {
			updateRow();
		} else if (event.getSource() == btnDelete) {
			deleteRow();
		}

		showTeam01();
	}
	// 2. books테이블의 모든 내용을 받아온다. 이때 테이블뷰에 입력하기 위해서 observablelist를 사용
	public ObservableList<UserData> getTeam01List() {
		// fx에서 테이블뷰에 표시하기 위한 리스트로 ObservableList 사용
		ObservableList<UserData> team01List = FXCollections.observableArrayList();
		// sql 작성
		String sql = "SELECT * FROM Userdata ORDER BY id";
		Connection conn = Singelton.getDBConnect(); // 위에서 만든 DB연결 메소드
		Statement stmt; // DB에 보낼 쿼리 객체 선언
		ResultSet rs; // DB에서 받아오는 결과객체 선언

		try {
			stmt = conn.createStatement(); // 쿼리 객체 생성
			rs = stmt.executeQuery(sql); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
			// 결과를 한 행씩 읽어서 bookList에서 입력
			UserData team01;
			while (rs.next()) {
				team01 = new UserData(rs.getString("name"), rs.getString("id"), rs.getString("password"),
						rs.getString("permit"));
				team01List.add(team01); // 북리스트에 하나의 book객체를 입력한다.
			}
		} catch (Exception e) {
			System.out.println("DB에서 sql문을 실행불가: " + e);
		}

		return team01List;
	}

	public void showTeam01() {
		ObservableList<UserData> list = getTeam01List();
		// 테이블뷰에 리스트를 넣고
		tvTeam01.setItems(list);
		// 각각의 열에 데이터를 불러오는 코드를 작성
		colEmail.setCellValueFactory(new PropertyValueFactory<UserData, String>("name"));
		colPass.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
		colFname.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
		colHost.setCellValueFactory(new PropertyValueFactory<UserData, String>("permit"));

	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// 프로그램 실행시 아래 내용이 실행됨
		showTeam01();
		// 마우스로 테이블셀을 클릭시 그 행의 내용이 다르면 이벤트를 발생한다.
		tvTeam01.getSelectionModel().selectedItemProperty().addListener(
				// 테이블안의 내용중 행이 하나의 book객체이고 다른 행을 선택(마우스클릭시)
				// 그전 행과 다를경우 이벤트 발생하고 showBookDetails를 실행한다.
				(obs, oldValue, newValue) -> showTeam01Details(newValue));
	}

	private void showTeam01Details(UserData team01) {
		if (team01 != null) {
			// 널값이 아닐때
			email.setText(team01.getId());
			password.setText(team01.getPassword());
			fullname.setText(team01.getname());
			host.setText(team01.getPermit());

		} else {
			// 널값일때 => 모든 tf를 내용을 지운다.
			email.setText("");
			password.setText("");
			fullname.setText("");
			host.setText("");

		}

	}

	// DB에 한줄 입력
	private void insertRow() {
		String sql = "INSERT INTO userdata VALUES(?,?,?,?)";

		Connection conn = Singelton.getDBConnect();
		PreparedStatement pstmt; // 쿼리 객체 선언(pstmt는 ?사용가능)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email.getText());
			pstmt.setString(2, password.getText());
			pstmt.setString(3, fullname.getText());
			pstmt.setString(4, host.getText());

			// 입력 준비됨
			pstmt.executeUpdate(); // 리턴값이 없을경우 업데이트 => 인서트 실행
			conn.commit(); // 한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("인서트 에러발생!");
		}
		email.setText("");
		password.setText("");
		fullname.setText("");
		host.setText("");

	}

	// DB의 행을 수정한다.
	private void updateRow() {
		String sql = "UPDATE UserData SET password=?, name=?, permit=? WHERE id=?";

		Connection conn = Singelton.getDBConnect();
		PreparedStatement pstmt; // 쿼리 객체 선언(pstmt는 ?사용가능)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password.getText()); // title
			pstmt.setString(2, fullname.getText()); // author
			pstmt.setString(3, host.getText()); // year

			pstmt.setString(4, email.getText()); // 아이디
			// 입력 준비됨
			pstmt.executeUpdate(); // 리턴값이 없을경우 업데이트 => 업데이트 실행
			conn.commit(); // 한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("업데이트중 에러발생!");
		}
	}

	private void deleteRow() {
		String sql = "DELETE FROM UserData WHERE id=?";
		Connection conn = Singelton.getDBConnect();
		PreparedStatement pstmt; // 쿼리 객체 선언(pstmt는 ?사용가능)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email.getText()); // 아이디
			// 입력 준비됨
			pstmt.executeUpdate(); // 리턴값이 없을경우 업데이트 => 삭제 실행
			conn.commit(); // 한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("삭제중 에러발생!");
		}
	}

}
