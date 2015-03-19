package com.mp.myspringjdbc;

//import java.sql.Connection;
//import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mp.dao.PatientCRUDInterfaceDao;
import com.mp.model.Patient;

public class PatientJDBCTemplate implements PatientCRUDInterfaceDao {
	
	private DataSource dataSource;
	private JdbcTemplate JdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){		
		this.dataSource = dataSource;		
		this.JdbcTemplateObject = new JdbcTemplate(dataSource);		
	}
	
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
				
					//Connection conn = creatConnection();
					//Statement statement = conn.createStatement();
					String queryString = "INSERT INTO Patient1 (fName, lName, bDate, country, city) VALUES (?,?,?,?,?)";			
					/*PreparedStatement ps = conn.prepareStatement(queryString);
					System.out.println("2");
					ps.setString(1, fName);
					ps.setString(2, lName);
					ps.setString(3, bDate);
					ps.setString(4, country);
					ps.setString(5, city);
					
					ps.executeUpdate();*/
					if(JdbcTemplateObject.update(queryString,fName,lName,bDate,country,city)==1){
						return "Added Successfully !";
					}else{
						return "Add Failed";
					}
					
			       // ResultSet rs = statement.executeQuery(queryString);
			        
				
			}
			
			
		}
	//////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<Patient> SearchPatient(){
		ArrayList<Patient> result =new ArrayList<Patient>();
			
			//Connection con = creatConnection();
			 String query = "select * from Patient1";
			// Statement stmt = con.createStatement();
			// PreparedStatement ps = con.prepareStatement(query);		
			 
			  List<Patient> patients = JdbcTemplateObject.query(query, new PatientMapper()); //stmt.executeQuery(query);			 
			 
			
				 
				 //System.out.println(s);
				 result = (ArrayList<Patient>) patients;
				 		
		
		return result;
	}
	
	
	public ArrayList<Patient> SearchPatient(String[] key, String[] value){
		
		ArrayList<Patient> result =new ArrayList<Patient>();
		
			//Connection con = creatConnection();
		 	StringBuilder str = new StringBuilder("select * from Patient1 where ");	 	
		 	
		 	int i=0;
		 	while(i<key.length){
		 		if(key[i]!=null){
		 			str.append(key[i]+"  =\'"+value[i]+"\'");
		 			str.append(" AND ");		 			
		 		}
		 		
		 		i++;
		 	}
		 	StringBuffer sb = new StringBuffer(str.toString());
		    sb.delete(sb.length()-4,sb.length()); 
		 	
		 	String query = sb.toString();
		 	System.out.println(query);
			 //String query = "select * from Patient1 where "+key+"  =\'"+value+"\'";
			 //PreparedStatement ps = con.prepareStatement(query);
			 //ps.setString(1, key);
			 //ps.setString(1, value);
			 //System.out.println(ps.toString()); 
			 List<Patient> patients = JdbcTemplateObject.query(query, new PatientMapper());
			 //ResultSet rs = ps.executeQuery();			 
			 		
			 result = (ArrayList<Patient>) patients;	 
			 return result;
			
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	
	public String PatientUpdate(int pId, String fName,String lNmae, String bDate, String country, String city){
		
		
			
			String query = "UPDATE Patient1 SET fName=?,lName=?,bDate=?,country=?,city=? WHERE pId=?";
			
			if(JdbcTemplateObject.update(query,fName,lNmae,bDate,country,city,pId)==1){
				return "Updated successfully !";
			}else{
				return "Update Failed !";
			}
			
			//ps.execute();
			
		
				
		}
////////////////////////////////////////////////////////////////////////////
	
	public String PatientDelete(String pId ,String fName, String lName, String bDate){
		
		
			//Connection con = creatConnection();
			String query = "Delete from Patient1 where pId=? AND fName=? AND lName=? AND bDate=?";
			
				if(JdbcTemplateObject.update(query, pId,fName,lName,bDate)==1){
					return "Deleted Successfully";
				}else{
					return "Delete Failed";
				}
				
				
			
			
			
			
		
		
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	}



	
