package com.mp.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Servlet implementation class PatientSearchServlet
 */
@WebServlet("/PatientSearchServlet")
public class PatientSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String country = request.getParameter("country");
		if(country!=null){
			response.setContentType("text/html");
			response.getWriter().write(generateJSONData(country));
		}
		
		
	}
	
	public String generateJSONData (String country){
		
		JSONParser parser = new JSONParser();
		StringBuffer returnData = null; 
		try {
	 
			Object obj = parser.parse(new FileReader("C:\\Users\\bsharp.BSTLANKA\\Desktop\\New folder\\New folder\\all_countries_cities_in_the_world.json"));
	 
			JSONObject jsonObject = (JSONObject) obj;	 
			
			
			JSONArray countrycityobjects = (JSONArray) jsonObject.get("country");
			Iterator<JSONObject> countrycityobjectsiterator = countrycityobjects.iterator();
			while (countrycityobjectsiterator.hasNext()) {
				JSONObject o = countrycityobjectsiterator.next();
				//JSONArray countries = (JSONArray) o.get("name");
				//Iterator<String> countriesiter = countries.iterator();
				
				String selectedCountry = (String) o.get("name");
				
				if(selectedCountry.equalsIgnoreCase(country)){
					//returnData = null;
					returnData = new StringBuffer("{\"country\":{");
					returnData.append("\"name\":\""+selectedCountry+"\",");
					returnData.append("\"city\":[");
					returnData.append("{\"name\":\"Select City\"},");
					JSONArray cities = (JSONArray) o.get("cities");
					Iterator<String> city = cities.iterator();
					
					
					
					while (city.hasNext()) {
						//System.out.println(city.next());							
						String city1 = city.next();												
						
						if(!city.hasNext()){							
							returnData.append("{\"name\":\""+city1+"\"}]");
							returnData.append("}}");							
						}else{
							returnData.append("{\"name\":\""+city1+"\"},");
						}
						
						
						
					}
					
				}else if(country.equalsIgnoreCase("Select Country")){
					returnData = new StringBuffer("{\"country\":{");
					returnData.append("\"name\":\"Select Country\",");
					returnData.append("\"city\":[");					
					returnData.append("{\"name\":\"Select City\"}]");
					returnData.append("}}");
				}
				
				
				
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/*StringBuffer returnData = null;
		if(country.equalsIgnoreCase("India")){
			returnData = new StringBuffer("{\"country\":{");
			returnData.append("\"name\":\"india\",");
			returnData.append("\"city\":[");
			returnData.append("{\"name\":\"Himalaya\"},");
			returnData.append("{\"name\":\"Dilli\"},");
			returnData.append("{\"name\":\"Hidrabaad\"},");
			returnData.append("{\"name\":\"Bombey\"}]");
			returnData.append("}}");
		}else if(country.equalsIgnoreCase("US")){
			returnData = new StringBuffer("{\"country\":{");
			returnData.append("\"name\":\"US\",");
			returnData.append("\"city\":[");
			returnData.append("{\"name\":\"Texas\"},");
			returnData.append("{\"name\":\"Las Wegas\"}]");
			returnData.append("}}");
		}else{
			
			
		}*/
		return returnData.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
