package com.mp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mp.dao.PatientAddDao;
import com.mp.model.Patient;


@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String bDate = request.getParameter("birthDate");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		
		Patient p = new Patient();
		p.setFname(fName);
		p.setLname(lName);
		p.setDate(bDate);
		p.setCountry(country);
		p.setCity(city);
		
		//System.out.println(fName);
		//System.out.println("@@@@@@@@@@@@@@@@@Servlet");
		
		//System.out.println("Fname   "+p.getFname());		
		//System.out.println("lName   "+p.getLname());
		//System.out.println("bdate   "+p.getDate());
		//System.out.println("Country   "+p.getCountry());
		//System.out.println("City   "+p.getCity());
		
		
		PatientAddDao padd = new PatientAddDao();
		String msg = padd.AddPatient(p);
		System.out.println("After add in servlet");
		
		response.setContentType("text/html");
		if(msg!=null){
			response.getWriter().write(msg);
		}else{
			response.getWriter().write("Add failed");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
