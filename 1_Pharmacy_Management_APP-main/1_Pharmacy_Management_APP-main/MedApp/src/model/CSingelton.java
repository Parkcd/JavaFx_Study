package model;

import java.sql.Connection;
import java.util.ArrayList;

import model.DBConnect;

public class CSingelton {
	private static CSingelton printer = null;
	private static int ManagerOrViewerstatic;
	// 싱글톤 클래스에서 로그인 상태 담당
	// 2020 12 10 김태헌
	private static Connection conn;
	private static DBConnect connect = new DBConnect();
	private static String strSearchText = "";
	// 싱글톤 클래스에서 DB 커넥트 담당
	// 한번 연결하면 계속 연결된 상태 유지할 수 있게
	// 2020 12 11 김태헌

	public static ArrayList<String> AryList = new ArrayList<>();
	public static ArrayList<String> AryList2 = new ArrayList<>();
	public static CSingelton getInstance() {
		if(printer == null) {

			conn = connect.getConnection();
			//최초 싱글톤 객체 생성될 떄 DB 연결
			printer = new CSingelton();
		}
		return printer;
	}

	public int getManagerOrViewer() {
		return ManagerOrViewerstatic;
	}
	public void setManagerOrViewer(int ManagerOrViewer) {
		ManagerOrViewerstatic = ManagerOrViewer;
	}

	public Connection getDBConnect() {
		return conn;
	}
	public void setSearchText(String text) {
		strSearchText = text;
	}
	public String getSearchText() {
		return strSearchText;
	}
}
