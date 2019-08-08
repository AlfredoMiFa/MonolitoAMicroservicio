package com.practica.employee.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.employee.exception.EmployeeNotFoundException;
import com.practica.employee.exception.model.Employee;
import com.practica.employee.exception.model.Workstation;
import com.practica.employee.repository.EmployeeRepository;
import com.practica.employee.service.EmployeeService;
import com.practica.employee.utils.HRUtils;
import com.practica.employee.workstation.exception.WorkstationException;
import com.practica.employee.workstation.exception.WorkstationNotFoundException;
import com.practica.employee.workstation.webclient.WorkstationWebClient;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WorkstationWebClient workstationWebClient;

	@Override
	public Employee retrieveById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado"));
		
		Workstation ws = workstationWebClient.findByEmployeeId(employee.getId()).orElse(null);

		if (ws != null) {
			employee.setWorkstation(ws);
		}
		
		return employee;
	}

	@Override
	public List<Employee> retrieveAll() {
		List<Employee> listemployee = employeeRepository.findAllByOrderByIdAsc();

		for (Employee employee : listemployee) {
			Workstation ws = workstationWebClient.findByEmployeeId(employee.getId()).orElse(null);

			if (ws != null) {
				employee.setWorkstation(ws);
			}
		}

		return listemployee;
	}

	@Override
	public Employee register(Employee employee) {

		employee.setId(0);

		Workstation ws = employee.getWorkstation();

		if (ws != null && ws.getId() > 0) {

			ws = workstationWebClient.findById(ws.getId())
					.orElseThrow(() -> new WorkstationNotFoundException("Workstation no encontrado"));

			if (ws.getEmployeeId() != null) {
				Employee assignedEmlpoyee = employeeRepository.findById(ws.getEmployeeId()).orElse(null);
				if (assignedEmlpoyee == null) {
					employee.setWorkstation(ws);
				} else {
					throw new WorkstationException(
							"Workstation no asignado, liksto para ser asignado a otro empleado");
				}
			} else {
				employee.setWorkstation(ws);
			}

			employee.setEmployeeNumber(HRUtils.nextEmployeeNumber());
			employee = employeeRepository.save(employee);

			ws.setEmployeeId(employee.getId());

			ws = workstationWebClient.update(ws);

		} else if (ws != null && ws.getId() == 0) {

			employee.setEmployeeNumber(HRUtils.nextEmployeeNumber());

			employee = employeeRepository.save(employee);

			ws.setFacilitiesSerialNumber(HRUtils.nextFacilitiesSerialNumber());

			ws.setEmployeeId(employee.getId());

			ws = workstationWebClient.save(ws);

			employee.setWorkstation(ws);

		} else if (ws == null) {

			employee.setEmployeeNumber(HRUtils.nextEmployeeNumber());

			employee = employeeRepository.save(employee);
		}

		return employee;
	}

	@Override
	public Employee update(Employee employee) {

		Employee registeredEmployee = employeeRepository.findById(employee.getId())
				.orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado"));

		registeredEmployee.setFirstName(employee.getFirstName());
		registeredEmployee.setLastName(employee.getLastName());

		Workstation workstation = employee.getWorkstation();
		Workstation registeredWorkstation = null;

		if (workstation != null && workstation.getId() > 0) { // workstation exists...

			registeredWorkstation = workstationWebClient.findById(workstation.getId())
					.orElseThrow(() -> new WorkstationNotFoundException("Workstation no encontrado"));

			if (workstation.getEmployeeId() != null) {
				Employee assignedEmlpoyee = employeeRepository.findById(workstation.getEmployeeId()).get();
				if (assignedEmlpoyee == null) {

					registeredEmployee.setWorkstation(registeredWorkstation);
					registeredWorkstation.setEmployeeId(registeredEmployee.getId());

					registeredEmployee = employeeRepository.save(registeredEmployee);
					registeredWorkstation = workstationWebClient.update(registeredWorkstation);

				} else if (assignedEmlpoyee != null && assignedEmlpoyee.getId() == registeredEmployee.getId()) {
																			

					registeredEmployee = employeeRepository.save(registeredEmployee);
					registeredWorkstation = workstationWebClient.save(registeredWorkstation);

				} else {
					throw new WorkstationException(
							"Workstation no asignado, liksto para ser asignado a otro empleado");
				}
			} else {
				registeredEmployee.setWorkstation(registeredWorkstation);
				registeredWorkstation.setEmployeeId(registeredEmployee.getId());

				registeredEmployee = employeeRepository.save(registeredEmployee);
				registeredWorkstation = workstationWebClient.update(registeredWorkstation);
			}
		} else if (workstation != null && workstation.getId() == 0) {

			workstation.setFacilitiesSerialNumber(HRUtils.nextFacilitiesSerialNumber());

			workstation.setEmployeeId(registeredEmployee.getId());

			workstation = workstationWebClient.save(workstation);

			registeredEmployee.setWorkstation(workstation);

			registeredEmployee = employeeRepository.save(registeredEmployee);

		} else if (workstation == null) {

			registeredWorkstation = registeredEmployee.getWorkstation();

			System.out.println("reigstered ws " + registeredWorkstation);

			if (registeredWorkstation != null) {
				registeredWorkstation.setEmployeeId(null);
				registeredWorkstation = workstationWebClient.save(registeredWorkstation);
			}

			registeredEmployee.setWorkstation(null);

			registeredEmployee = employeeRepository.save(registeredEmployee);
		}

		return registeredEmployee;
	}

	@Override
	public Employee partialUpdate(long employeeId, Employee partialEmployee) {
		return null;
	}

	@Override
	public Employee delete(Employee employee) {

		Workstation ws = employee.getWorkstation();
		if (ws != null) {
			ws.setEmployeeId(null);
			workstationWebClient.save(ws);
		}

		employeeRepository.delete(employee);
		return employee;
	}

	@Override
	public List<Employee> retrieveByFirstName(String firstName) {
		return employeeRepository.findByFirstNameIgnoreCase(firstName);
	}

	@Override
	public List<Employee> retrieveByFirstNameAndLastName(String firstName, String lastName) {
		return employeeRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
	}

	@Override
	public Employee retrieveByEmployeeNumber(String employeeNumber) {
		return employeeRepository.findByEmployeeNumber(employeeNumber);
	}

}
