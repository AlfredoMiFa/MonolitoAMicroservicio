package com.practica.workstation.service;

import java.util.List;

import com.practica.workstation.model.Workstation;

public interface WorkstationService {
	
	Workstation retrieveById(Long id);

	List<Workstation> retrieveAll();

	Workstation register(Workstation workstation);

	Workstation update(Workstation workstation);

	Workstation delete(Workstation workstation);

	List<Workstation> retrieveByVendor(String vendor);

	List<Workstation> retrieveByFacilitiesSerialNumber(String facilitiesSerialNumber);

	Workstation retrieveByEmployeeId(long employeeId);
	
}
