package com.mp.myspringjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mp.model.Patient;

public class PatientMapper implements RowMapper<Patient> {
	
	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException{
		Patient p = new Patient();
		p.setPid(rs.getInt("pId"));
		p.setFname(rs.getString("fName"));
		p.setLname(rs.getString("lName"));
		p.setDate(rs.getString("bdate"));
		p.setCountry(rs.getString("country"));
		p.setCity(rs.getString("city"));
				
		return p;
	}

}
