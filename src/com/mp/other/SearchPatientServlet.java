package com.mp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mp.dao.PatientSearchDao;

@WebServlet("/SearchPatientServlet")
public class SearchPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String bDate = request.getParameter("birthDate");
		String country = request.getParameter("country");
		//String city = request.getParameter("city");
		
		PatientSearchDao ps = new PatientSearchDao();
		System.out.println(country);
		//System.out.println(city);
		ArrayList<String []> result = new ArrayList<String []>();
		if(fName!=""){
			System.out.println(1);
			result = ps.SearchPatient("fName",fName);
		}else if(lName!=""){
			System.out.println(2);
			result = ps.SearchPatient("lName",lName);
		}else if(bDate!=""){	
			System.out.println(3);
			result = ps.SearchPatient("bDate",bDate);
		}else if(!country.equalsIgnoreCase("Select Country")){
			System.out.println(4);
			result = ps.SearchPatient("country",country);
		}else{
			System.out.println(6);
			result = ps.SearchPatient();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int i = 0;
		
		
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
			String tdarr[] =result.get(i++);
			while(j<tdarr.length){					
				out.print("<td>"+tdarr[j++] +"</td>");
			}
			
			out.println("</tr>");
			//System.out.println("In while");
			//String s = result.get(i++);
			//System.out.println(s);
			
		}
		out.println("</tbody>");
		out.println("<table>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
