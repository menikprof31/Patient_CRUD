package com.mp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mp.dao.PatientDeleteDao;


@WebServlet("/DeletePatientServlet")
public class DeletePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("patientId");
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String bDate = request.getParameter("birthDate");
		//String country = request.getParameter("country");
		//String city = request.getParameter("city");
		
		System.out.println(pId);
		
		PatientDeleteDao pdd = new PatientDeleteDao();
		String msg = pdd.PatientDelete(pId, fName, lName, bDate);
		response.setContentType("text/html");
		System.out.println("messsage"+msg);
		if(msg!=null){
			response.getWriter().write(msg);
		}else{
			response.getWriter().write("Delete failed");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
