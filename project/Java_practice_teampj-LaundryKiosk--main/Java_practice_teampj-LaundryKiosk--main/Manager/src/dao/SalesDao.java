package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Machine;
import dto.count;
import dto.date;
import dto.temptable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalesDao {
	private Connection con; // DB연동시 사용되는 클래스 : DB연동클래스
	private PreparedStatement ps; // 연결된 DB내 SQL 조작 할때 사용되는 인터페이스 : DB조작인터페이스
	private ResultSet rs; // 결과물을 조작하는 인터페이스 
	
	public static SalesDao salesDao = new SalesDao(); // DB 연동 객체;
	
	public SalesDao() {
		try {
			// DB연동 
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://database-1.ctq8tels7lkd.us-east-1.rds.amazonaws.com:3306/javafx?serverTimezone=UTC",
					"focks","akfrdmsfocks0626!!$LLH"); // 2. DB 주소 연결 
		}
		catch(Exception e ) { System.out.println( "[DB 연동 오류]"+e  ); }
	}
	
	public ArrayList<count> frequency() {
		ArrayList<count> count = new ArrayList<>();
		try {
			String sql = "select * from javafx.sales";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();		
			while(rs.next()) {
				count.add(new count(rs.getInt(3),null));	
			}
			for(count temp : count) {
				sql = "select cname from javafx.category where cnum = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, temp.getCnum());
				rs = ps.executeQuery();
				if(rs.next()) {
					temp.setCname(rs.getString(1));
				}
			}		
			return count;
		} catch (Exception e) {
			System.out.println("[sql에러]" + e);
		}
		return null;
	}
	public ObservableList<temptable> tlist(Machine machine){
		
		ObservableList<temptable> tlist = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM javafx.category where mnum = "+machine.getMnum();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				temptable tem = new temptable(machine.getMnum(),rs.getString(4),machine.getMamount(),machine.getMphone(),machine.getMtemperature(),machine.getMdegree(), 0, rs.getInt(5));
				tlist.add(tem);
			}
			return tlist;
		} catch (Exception e) {
			System.out.println("[sql에러]" + e);
		}
		return null;
	}
	public ArrayList<date> frequency2() {
		ArrayList<date> date = new ArrayList<>();
		try {
			String sql = "select sdate, count(sdate), sum(sprice) as date from javafx.sales group by sdate";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				date dat = new date(rs.getString(1), rs.getInt(3));
				date.add(dat);
			}
			return date;
		} catch (Exception e) {
			System.out.println("[sql에러]" + e);
		}
		return null;
	}
	
	
}
