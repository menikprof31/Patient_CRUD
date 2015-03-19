package com.mp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;


import com.mp.model.Patient;

public class PatientAddDao {
	
	public String AddPatient(Patient p) {
		
		String fName = p.getFname();
		String lName = p.getLname();
		String bDate = p.getDate();
		String country = p.getCountry();
		String city = p.getCity();
		
		//System.out.println(bDate.length()+"");
		String message="", returnMessage;
		if(fName=="" && lName=="" && bDate=="" && country.equalsIgnoreCase("Select Country")){
			return "Please fill data";
		}else if(fName=="" || lName=="" || bDate=="" || country.equalsIgnoreCase("Select Country")){
			System.out.println("Else");
			int count = 0;
			if(fName==""){
				message +=  "First Name, "; 
				count++;
			}
			if(lName == ""){
				message += "Last Name, ";
				count++;
			}
			if(bDate == ""){
				message += "Birth Date, ";
				count++;
			}
			if(country.equalsIgnoreCase("Select Country")){
				message += "Country, ";
				count++;
			}
			if(count<4){
				System.out.println("Else IF count");
				System.out.println("Message"+message);
				//StringBuilder sb = new StringBuilder(message);
				//sb.deleteCharAt(message.length());
				//sb.deleteCharAt(message.length()-1);
				//returnMessage = sb.toString();
				returnMessage = message;
				System.out.println(returnMessage);
			}else{
				returnMessage=message;
			}
			
			System.out.println(returnMessage);
			return "Plese Enter Your "+returnMessage+"";
		}else{
			try {
				Connection conn = creatConnection();
				//Statement statement = conn.createStatement();
				String queryString = "INSERT INTO Patient1 (fName, lName, bDate, country, city) VALUES (?,?,?,?,?)";			
				PreparedStatement ps = conn.prepareStatement(queryString);
				System.out.println("2");
				ps.setString(1, fName);
				ps.setString(2, lName);
				ps.setString(3, bDate);
				ps.setString(4, country);
				ps.setString(5, city);
				
				ps.executeUpdate();			
				return "Add Successfully";
		       // ResultSet rs = statement.executeQuery(queryString);
		        
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("@@@@@CAtch");
				e.printStackTrace();
				return null;
			}
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
