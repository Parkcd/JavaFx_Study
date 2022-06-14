package view;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DBConnect;
import model.Medicine;
import model.CSingelton;

public class MedAppDao {

	private static Connection con;
	private PreparedStatement pstmt;
	private ObservableList<Medicine> data;


	CSingelton Singelton = CSingelton.getInstance();

	public MedAppDao() {
		DBConnect connect = new DBConnect();
		if (con == null) {
			con = connect.getConnection();
			}
		}


public ObservableList<Medicine> getData() {
	try {
		pstmt = con.prepareStatement("select * from Medicine");

		data = FXCollections.observableArrayList();
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()){

			Medicine medicine = new Medicine(rs.getString("name"), rs.getString("character"),
					rs.getString("effct"), rs.getString("effct"), rs.getString("warning"),
					rs.getString("company"), Date.valueOf(rs.getString("expiration")),
					Integer.parseInt(rs.getString("price")),
					Integer.parseInt(rs.getString("stock"))
					);

//			medicine.setName(rs.getString("name"));
//			medicine.setCharacter(rs.getString("character"));
//			medicine.setEffect(rs.getString("effct"));
//			medicine.setWarning(rs.getString("warning"));
//			medicine.setCompany(rs.getString("company"));
//			medicine.setExpiration(Date.valueOf(rs.getString("expiration")));
//			medicine.setPrice(Integer.parseInt(rs.getString("price")));
//			medicine.setStock(Integer.parseInt(rs.getString("stock")));

			data.add(medicine);
			}
		}

	catch (Exception e){
		e.printStackTrace();
		System.out.println("Dao.getData():Error on Building Data");
		return null;

		}
		return data;
	}


	public ObservableList<Medicine> searchByName(String name){

		String sql = "select * from Medicine where name like ?";

		name = "%" + name + "%";

		try {

			data = FXCollections.observableArrayList();

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name);

			ResultSet rs=pstmt.executeQuery();


			while (rs.next()) {
				String str = rs.getString("expiration");
				String strCut = str.substring(0, 11);


				Singelton.AryList.add(rs.getString("image"));

				Medicine medicine = new Medicine(rs.getString("name"), rs.getString("image"),
						rs.getString("character"), rs.getString("effect"), rs.getString("warning"),
						rs.getString("company"),strCut,
						Integer.parseInt(rs.getString("price")),
						Integer.parseInt(rs.getString("stock"))
						);

//					medicine.setName(rs.getString("name"));
//					medicine.setCharacter(rs.getString("character"));
//					medicine.setEffect(rs.getString("effect"));
//					medicine.setWarning(rs.getString("warning"));
//					medicine.setCompany(rs.getString("company"));
//					medicine.setExpiration(rs.getString("expiration"));
//					medicine.setPrice(rs.getString("price"));
//					medicine.setStock(rs.getString("stock"));
					data.add(medicine);
			}
			rs.close();
			pstmt.close();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Dao.getData():Error on Building Data");
			return null;

			}
			return data;
	}
}
