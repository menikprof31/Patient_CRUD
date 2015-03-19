package com.mp.dao;

import java.util.ArrayList;

import com.mp.model.Patient;

public interface PatientCRUDInterfaceDao {
	
	abstract String AddPatient(Patient p);
	
	abstract ArrayList<Patient> SearchPatient();
	
	abstract ArrayList<Patient> SearchPatient(String[] key, String[] value);
	
	abstract String PatientUpdate(int pId, String fName,String lNmae, String bDate, String country, String city);
	
	abstract String PatientDelete(String pId ,String fName, String lName, String bDate);
}
