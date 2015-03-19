package com.mp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientUpdateDao {
	
	public String PatientUpdate(int pId, String fName,String lNmae, String bDate, String country, String city){
		
		try {
			Connection con = creatConnection();
			String query = "UPDATE Patient1 SET fName=?,lName=?,bDate=?,country=?,city=? WHERE pId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fName);
			ps.setString(2, lNmae);
			ps.setString(3, bDate);
			ps.setString(4, country);
			ps.setString(5, city);
			ps.setInt(6, pId);
			
			ps.execute();
			return "Updated successfully !";
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return "Error updating";
		}
				
	}
	
	public Connection creatConnection() throws ClassNotFoundException, SQLException{
		String userName = "sa";
		String password = "N0m0r3Br1@n";		

		String url = "jdbc:sqlserver://192.168.94.87:1433;databaseName=BcareTraining";		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
		Connection conn = DriverManager.getConnection(url, userName, password);
		return conn;	
		
		
	}

}
