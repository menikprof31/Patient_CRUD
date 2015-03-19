package com.mp.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;












import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mp.control.PojoMapper;
import com.mp.dao.PatientCRUDDao;
import com.mp.dao.PatientCRUDInterfaceDao;
import com.mp.model.Json;
import com.mp.model.Patient;
import com.mp.myspringjdbc.PatientJDBCTemplate;

@WebServlet("/PatientCRUDServlet")
public class PatientCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		
		String buttonController = request.getParameter("buttonController");
		String pId = "";
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String bDate = "" ;		
		String country = "";
		String city = "";
		String beforeDateValid = request.getParameter("birthDate");
		//bDate = beforeDateValid;
		//System.out.println("beforeDateValid"+beforeDateValid);
		
		if(beforeDateValid == ""){
			bDate = "";
		}else if(isThisDateValid(beforeDateValid, "MM/dd/yyyy")){
			bDate = beforeDateValid;
		}else{
			bDate = "00/00/0000";
		}
				
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		PatientCRUDInterfaceDao pcid;
		pcid = (PatientJDBCTemplate) context.getBean("patientJDBCTemplate");
		//pcid = new PatientCRUDDao();
		if(!buttonController.equalsIgnoreCase("delete")){
			country = request.getParameter("country");
		}
		if(!buttonController.equalsIgnoreCase("delete")){
			city = request.getParameter("city");
		}
		
		if(buttonController.equalsIgnoreCase("delete") || buttonController.equalsIgnoreCase("update")){
			pId = request.getParameter("patientId");
		}
		
		if(buttonController.equalsIgnoreCase("add")){
			
			Patient p = new Patient();
			p.setFname(fName);
			p.setLname(lName);
			p.setDate(bDate);
			p.setCountry(country);
			p.setCity(city);			
					
			
				      
						
			//PatientCRUDDao padd = new PatientCRUDDao();
			//String msg = padd.AddPatient(p);
			String msg = pcid.AddPatient(p);
			System.out.println("After add in servlet");
			
			response.setContentType("text/html");
			if(msg!=null){
				response.getWriter().write(msg);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "Added");
				session.setAttribute("transactionName", fName);
			}else{
				response.getWriter().write("Add failed");
			}
			
		}else if(buttonController.equalsIgnoreCase("search")){
			//PatientCRUDDao ps = new PatientCRUDDao();
			System.out.println(country);
			//System.out.println(city);
			ArrayList<Patient> result = new ArrayList<Patient>();
			String key[] = new String[5];
			String value[] = new String[5];
			if(fName!="" && fName!=null){
				key[0] = "fName";
				value [0] = fName;
			}				
			if(lName!="" && lName!=null ){
				key[1] = "lName";
				value [1] = lName;
			}
			if(bDate!="" && bDate!=null){
				key[2] = "bDate";
				value [2] = bDate;
			}
			if(!country.equalsIgnoreCase("Select Country")){
				key[3] = "country";
				value [3] = country;
			}
			if(!city.equalsIgnoreCase("Select City")){
				key[4] = "city";
				value [4] = city;
			}
			
			
			if(fName!="" && fName!=null){
				//System.out.println(1);
				
				result = pcid.SearchPatient(key,value);//ps.SearchPatient("fName",fName);
				
				
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "SearchedBy");
				session.setAttribute("transactionName", fName);
			}else if(lName!=""){
				//System.out.println(2);
				result = pcid.SearchPatient(key,value);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "SearchedBy");
				session.setAttribute("transactionName", lName);
			}else if(bDate!=""){	
				//System.out.println(3);
				result = pcid.SearchPatient(key,value);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "SearcedhBy");
				session.setAttribute("transactionName", bDate);
			}else if(!country.equalsIgnoreCase("Select Country")){
				//System.out.println(4);
				result = pcid.SearchPatient(key,value);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "SearchedBy");
				session.setAttribute("transactionName", country);
			}else if(!city.equalsIgnoreCase("Select City")){
				//System.out.println(4);
				result = pcid.SearchPatient(key,value);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "SearchedBy");
				session.setAttribute("transactionName", city);
			}else{
				//System.out.println(6);
				result = pcid.SearchPatient();
				//session.setAttribute("transactiontype", "SearchedAll");
				//session.setAttribute("transactionName", lName);
			}
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			int i = 0;
			
			//ObjectMapper mapper = new ObjectMapper();
			//mapper.writeValueAsString(arg0);
			Json jsonObj = new Json();
			 //String pojoAsString = PojoMapper.toJson(jsonObj, true);
			 //FileWriter fw = new FileWriter("E:\\pojo.txt");
			   //PojoMapper.toJson(jsonObj, fw, true);
			// JavaTypeMapper mapper = new JavaTypeMapper();			   
			ObjectMapper mapper = new ObjectMapper();
			 
			try {
		          mapper.writeValue(new File("E:\\jackson.json"), jsonObj);
			} catch (JsonGenerationException e) {
				System.out.println("JsonGenerationException1");
			} catch (JsonMappingException e) {
				System.out.println("JsonMappingException2");
			} catch (IOException e) {
				System.out.println("IOException3");
			}
			
			out.println("<table id=\"example\" class=\"display dataTable\" cellspacing=\"0\" style=\"width:800px;border:solid 1px\">");
			out.println("<thead><tr>");
			out.print("<th>Patient Id</th>");
			out.print("<th>First Name</th>");
			out.print("<th>Last Name</th>");
			out.print("<th>Birth Date</th>");
			out.print("<th>Address</th>");
			out.print("<th>Country</th>");
			out.print("<th>City</th>");
			out.println("</tr><thead>");
			out.println("<tbody>");
			
			while (i<result.size()) {
				//response.getWriter().write(result.get(i++));
				String bgclr;
				if(i%2 == 0){
					bgclr = "#F1F0F1";
				}else{
					bgclr = "white";
				}
				out.println("<tr onclick=\"run()\" style=\"background-color:"+bgclr+" \">");
				int j = 0;
				Patient getP =result.get(i++);
				//while(j<tdarr.length){					
					out.print("<td>"+getP.getPid() +"</td>");
					out.print("<td>"+getP.getFname() +"</td>");
					out.print("<td>"+getP.getLname() +"</td>");
					out.print("<td>"+getP.getDate() +"</td>");
					out.print("<td>"+ "null" +"</td>");
					out.print("<td>"+getP.getCountry() +"</td>");
					out.print("<td>"+getP.getCity() +"</td>");
					
				//}
				
				out.println("</tr>");
				//System.out.println("In while");
				//String s = result.get(i++);
				//System.out.println(s);
				
			}
			out.println("</tbody>");
			out.println("<table>");
		
		}else if(buttonController.equalsIgnoreCase("update")){
			//PatientCRUDDao pud = new PatientCRUDDao();
			String msg = pcid.PatientUpdate(Integer.parseInt(pId),fName, lName, bDate, country, city);
			
			response.setContentType("text/html");
			if(msg!=null){
				response.getWriter().write(msg);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "Updated");
				session.setAttribute("transactionName", fName);
			}
			
		}else if(buttonController.equalsIgnoreCase("delete")){
			//PatientCRUDDao pdd = new PatientCRUDDao();
			String msg = pcid.PatientDelete(pId, fName, lName, bDate);
			response.setContentType("text/html");
			System.out.println("messsage"+msg);
			if(msg!=null){
				response.getWriter().write(msg);
				session.removeAttribute("transactiontype");
				String date = sdf.format(new Date()); 
				session.setAttribute("transactionTime", date);
				session.setAttribute("transactiontype", "Deleted");
				System.out.println(session.getAttribute("transactiontype"));
				session.setAttribute("transactionName", fName);
			}else{
				response.getWriter().write("Delete failed");
			}
		}
		
		if(buttonController.equalsIgnoreCase("SESSION")){
			String s = (String)session.getAttribute("transactiontype") +" : "+ session.getAttribute("transactionName")+ " at : "+session.getAttribute("transactionTime");
			response.getWriter().write(s );
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public boolean isThisDateValid(String dateToValidate, String dateFromat){
		
		if(dateToValidate == null || dateToValidate.equalsIgnoreCase("")){
			return false;
		}
 
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
 
		try {
 
			//if not valid, it will throw ParseException
			//System.out.println(dateToValidate+"   "+ dateFromat);
			Date date = sdf.parse(dateToValidate);
			//System.out.println("Date after validation: " + date);
			//System.out.println(date);
 
		} catch (ParseException e) {
			
			e.printStackTrace();
			return false;
		}
 
		return true;
	}

}
