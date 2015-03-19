package com.mp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mp.model.Patient;

public class PatientCRUDDao implements PatientCRUDInterfaceDao {

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<Patient> SearchPatient(){
		ArrayList<Patient> result =new ArrayList<Patient>();
		try {			
			Connection con = creatConnection();
			 String query = "select * from Patient1";
			 Statement stmt = con.createStatement();
			// PreparedStatement ps = con.prepareStatement(query);		
			 
			 ResultSet rs = stmt.executeQuery(query);			 
			 
			 while(rs.next()){
								 
				 Patient p = new Patient();
				 p.setPid(rs.getInt("pId"));
				 p.setFname(rs.getString("fName"));
				 p.setLname(rs.getString("lName"));
				 p.setDate(rs.getString("bdate"));
				 p.setCountry(rs.getString("country"));
				 p.setCity(rs.getString("city"));
				
				 result.add(p);
			 }
			 return result;
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Patient> SearchPatient(String[] key, String[] value){
		
		ArrayList<Patient> result =new ArrayList<Patient>();
		try {			
			Connection con = creatConnection();
			
			StringBuilder str = new StringBuilder("select * from Patient1 where ");	 	
		 	
		 	int i=0,j=0,x=1;
		 	while(i<key.length){
		 		if(key[i]!=null){
		 			str.append(key[i]+"  =?");
		 			str.append(" AND ");		 			
		 		}
		 		
		 		i++;
		 	}
		 	StringBuffer sb = new StringBuffer(str.toString());
		    sb.delete(sb.length()-4,sb.length()); 
			
			 String query = sb.toString();
			 PreparedStatement ps = con.prepareStatement(query);
			 //ps.setString(1, key);
			 while(j<key.length){
			 		if(key[j]!=null){
			 			 ps.setString(x++, value[j]);		 			
			 		}
			 		
			 		j++;
			 	}
			
			 //System.out.println(ps.toString()); 
			 
			 ResultSet rs = ps.executeQuery();			 
			 
			 while(rs.next()){
				 Patient p = new Patient();
				 p.setPid(rs.getInt("pId"));
				 p.setFname(rs.getString("fName"));
				 p.setLname(rs.getString("lName"));
				 p.setDate(rs.getString("bdate"));
				 p.setCountry(rs.getString("country"));
				 p.setCity(rs.getString("city"));
				
				 result.add(p);
			 }
			 return result;
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Connection creatConnection() throws ClassNotFoundException, SQLException{
		String userName = "sa";
		String password = "N0m0r3Br1@n";		

		String url = "jdbc:sqlserver://192.168.94.87:1433;databaseName=BcareTraining";		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
		Connection conn = DriverManager.getConnection(url, userName, password);
		return conn;	
		
		
	}
}

