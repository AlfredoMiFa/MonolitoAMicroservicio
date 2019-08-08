package com.practica.workstation.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practica.workstation.model.Workstation;
import com.practica.workstation.service.WorkstationService;

import lombok.Setter;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/v1/workstations")
public class WorkstationController {
	
	@Autowired
	private @Setter WorkstationService workstationService;

	@GetMapping
	public List<Workstation> getAllWorkstation() {
		return workstationService.retrieveAll();
	}

	@SneakyThrows
	@GetMapping("/{workstationId}")
	public Workstation getWorkstation(@PathVariable long workstationId) {
		// Thread.sleep(4000);
		return workstationService.retrieveById(workstationId);
	}

	@GetMapping("/search/findByEmployeeId")
	public Workstation getWorkstationByEmployeeId(@RequestParam(required = true) long employeeId) {
		return workstationService.retrieveByEmployeeId(employeeId);
	}

	@PostMapping
	public Workstation postWorkstation(@RequestBody Workstation workstation) {
		workstation.setId(0);

		return workstationService.register(workstation);
	}

	@PutMapping("/{workstationId}")
	public Workstation putWorkstation(@PathVariable long workstationId, @RequestBody Workstation workstation) {

		System.out.println("Received ws : " + workstation);

		workstation.setId(workstationId);

		return workstationService.update(workstation);
	}

	@DeleteMapping("/{workstationId}")
	public Workstation deleteWorkstation(@PathVariable long workstationId) {

		return workstationService.delete(workstationService.retrieveById(workstationId));
	}
	
}
