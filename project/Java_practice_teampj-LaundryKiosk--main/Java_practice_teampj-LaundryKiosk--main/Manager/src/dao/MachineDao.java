package dao;

import java.nio.channels.SelectionKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.MachineDetail;
import controller.MachineManagement;
import dto.Machine;
import dto.temptable;

public class MachineDao {
	private Connection con; // DB연동시 사용되는 클래스 : DB연동클래스
	private PreparedStatement ps; // 연결된 DB내 SQL 조작 할때 사용되는 인터페이스 : DB조작인터페이스
	private ResultSet rs; // 결과물을 조작하는 인터페이스 
	
	public static MachineDao machinedao = new MachineDao(); // DB 연동 객체;
	
	public MachineDao() {
		try {
			// DB연동 
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://database-1.ctq8tels7lkd.us-east-1.rds.amazonaws.com:3306/javafx?serverTimezone=UTC",
					"focks","akfrdmsfocks0626!!$LLH"); // 2. DB 주소 연결 
		}
		catch(Exception e ) { System.out.println( "[DB 연동 오류]"+e  ); }
	}
	//머신 저장
	public boolean update(temptable tb) {
		try {
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime()); // db date 타임 맞게 시간뽑기
			String sql = "update machine set mamount=?,mphone=?,mtemperature=?,mdegree=?,mtime=? where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tb.getMamount());
			ps.setString(2, tb.getMphone());
			ps.setString(3, tb.getMtemperature());
			ps.setString(4, tb.getMdegree());
			ps.setTimestamp(5,date);
			ps.setInt(6, tb.getMnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("[sql 에러]" + e);
		}
		return false;
	}
	//머신 불러오기
	public Machine load(int mnum) {
		try {
			String sql = "select * from machine where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			Machine machine = null;
			if(rs.next()) {
				LocalDateTime localDate1 = rs.getTimestamp(6).toLocalDateTime();// LocalDateTime <=> Timestamp	
			Machine temp = new Machine(	
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						localDate1,
						0
						);
			machine = temp;
			}
			return machine;
		} catch (Exception e) {
			System.out.println("[sql 에러]" + e);
		}
		return null;
	}
	// 머신 리스트
	public ArrayList<Machine> list(){
		ArrayList<Machine> list = new ArrayList<>();
		try {
			String sql = "select * from javafx.machine";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				LocalDateTime date = null;
				if(rs.getTimestamp(6) != null) {
					date = rs.getTimestamp(6).toLocalDateTime();
				}
				list.add(
						new Machine(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),date,0)
						);
			}
			return list;
		} catch (Exception e) {
			System.out.println("[sql 에러]" + e);
		}
		return null;
	}
	//머신 추가
	public int insert(String name) {
		try {	
			
			
			String sql = "insert into javafx.machine(mamount)value(?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 100);
			if(name.equals("null")) {
				sql = "insert into javafx.machine()value()";
				ps = con.prepareStatement(sql);
			}
			ps.executeUpdate();
			sql = "select last_insert_id();";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("[sql 에러]" + e);
		}
		return 0;
	}
	// 세제 추가
	public boolean add(int mnum, String ch) {
		try {
			String sql = "update machine set "+ch+" = ? where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, MachineManagement.table.getMamount() + 10);	
			ps.setInt(2, mnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("[sql 에러]" + e);
		}
		return false;
	}
}
