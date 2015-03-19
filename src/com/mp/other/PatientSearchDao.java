package com.mp.dao;


import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class PatientSearchDao {
	
	public ArrayList SearchPatient(){
		ArrayList<String []> result =new ArrayList<String []>();
		try {			
			Connection con = creatConnection();
			 String query = "select * from Patient1";
			 Statement stmt = con.createStatement();
			// PreparedStatement ps = con.prepareStatement(query);		
			 
			 ResultSet rs = stmt.executeQuery(query);			 
			 
			 while(rs.next()){
				 //System.out.println(rs.getString(6));
				 int i = 1, j=0;
				 String s[] = new String[7];
				 while(i<8){
					 
					 s[j++] = rs.getString(i++);
				 }
				 
				 //System.out.println(s);
				 result.add(s);
			 }
			 return result;
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList SearchPatient(String key, String value){
		
		ArrayList<String []> result =new ArrayList<String []>();
		try {			
			Connection con = creatConnection();
			 String query = "select * from Patient1 where "+key+"  =?";
			 PreparedStatement ps = con.prepareStatement(query);
			 //ps.setString(1, key);
			 ps.setString(1, value);
			 //System.out.println(ps.toString()); 
			 
			 ResultSet rs = ps.executeQuery();			 
			 
			 while(rs.next()){
				 //System.out.println(rs.getString(6));
				 int i = 1, j=0;
				 String s[] = new String[7];
				 while(i<8){
					 
					 s[j++] = rs.getString(i++);
				 }
				 System.out.println(s);
				 result.add(s);
			 }
			 return result;
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return result;
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
