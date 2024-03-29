package com.practica.workstation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.workstation.exception.WorkstationNotFoundException;
import com.practica.workstation.model.Workstation;
import com.practica.workstation.repository.WorkstationRepository;
import com.practica.workstation.service.WorkstationService;
import com.practica.workstation.util.HRUtils;

@Service
@Transactional
public class WorkstationServiceImpl implements WorkstationService {

	@Autowired
	private WorkstationRepository workstationRepository;

	@Override
	public Workstation retrieveById(Long id) {
		return workstationRepository.findById(id)
				.orElseThrow(() -> new WorkstationNotFoundException("Workstation not found"));
	}

	@Override
	public List<Workstation> retrieveAll() {
		return workstationRepository.findAll();
	}

	@Override
	public Workstation register(Workstation workstation) {
		workstation.setFacilitiesSerialNumber(HRUtils.nextFacilitiesSerialNumber());
		workstation = workstationRepository.save(workstation);
		return workstation;
	}

	@Override
	public Workstation update(Workstation workstation) {
		
		//verify current employee dont have assigned workstation
		Workstation ws = workstationRepository.findByEmployeeId(workstation.getEmployeeId());
		
		if(ws != null) {
			ws.setEmployeeId(null);
			workstationRepository.save(ws);
		}

		Workstation registeredWorkstation = workstationRepository.findById(workstation.getId())
				.orElseThrow(() -> new WorkstationNotFoundException("Workstation not found"));

		registeredWorkstation.setVendor(workstation.getVendor());
		registeredWorkstation.setModel(workstation.getModel());
		registeredWorkstation.setEmployeeId(workstation.getEmployeeId());
		
		registeredWorkstation = workstationRepository.save(registeredWorkstation);
		return registeredWorkstation;
	}

	@Override
	public Workstation delete(Workstation workstation) {
		workstationRepository.delete(workstation);
		return workstation;
	}

	@Override
	public List<Workstation> retrieveByVendor(String vendor) {
		return workstationRepository.findByVendor(vendor);
	}

	@Override
	public List<Workstation> retrieveByFacilitiesSerialNumber(String facilitiesSerialNumber) {
		return workstationRepository.findByFacilitiesSerialNumber(facilitiesSerialNumber);
	}

	@Override
	public Workstation retrieveByEmployeeId(long employeeId) {
		return workstationRepository.findByEmployeeId(employeeId);
	}

}
