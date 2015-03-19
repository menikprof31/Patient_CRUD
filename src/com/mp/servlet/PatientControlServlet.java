package com.mp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PatientControlServlet")
public class PatientControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET");
		response.setContentType("text/html");
		System.out.println(request.getParameter("a"));
		if(request.getParameter("a")!=null){
			response.getWriter().write((String)request.getParameter("a"));//(String)request.getAttribute("button1")
		}else{
			response.getWriter().write("Menik");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post");
		doGet(request, response);
	}

}
