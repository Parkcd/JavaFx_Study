package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "root";
	private final String PASSWRORD = "1234";

	Connection conn = null;

	public Connection getConnection() {
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2. DB연결
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWRORD);
			// DB 접속 메소드
			return conn; //연결되면 conn을 리턴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
