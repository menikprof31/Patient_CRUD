package com.mp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDeleteDao {
	
	public String PatientDelete(String pId ,String fName, String lName, String bDate){
		
		try {
			Connection con = creatConnection();
			String query = "Delete from Patient1 where pId=? AND fName=? AND lName=? AND bDate=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(pId));
			pst.setString(2, fName);
			pst.setString(3, lName);
			pst.setString(4, bDate);			
			
			pst.execute();
			return "Deleted Successfully";
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return "Connection to Database problem Or Error on deletion";
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
