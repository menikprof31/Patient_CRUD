package com.mp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mp.dao.PatientUpdateDao;


@WebServlet("/UpdatePatientServlet")
public class UpdatePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("patientId"));
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String bDate = request.getParameter("birthDate");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		
		PatientUpdateDao pud = new PatientUpdateDao();
		String msg = pud.PatientUpdate(pId,fName, lName, bDate, country, city);
		
		response.setContentType("text/html");
		if(msg!=null){
			response.getWriter().write(msg);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
