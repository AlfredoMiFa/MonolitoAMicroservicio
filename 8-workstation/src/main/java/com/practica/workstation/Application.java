package com.practica.workstation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.practica.workstation.model.Workstation;
import com.practica.workstation.repository.WorkstationRepository;
import com.practica.workstation.util.HRUtils;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	private WorkstationRepository workstationRepository;
	
	public Application(WorkstationRepository workstation) {
		this.workstationRepository=workstation;
	}
	
	@Bean
	public CommandLineRunner startup() {

		return (args) -> {

			Workstation workstation = Workstation.builder().vendor("Mac").model("Mac Book Pro 13 retina")
					.facilitiesSerialNumber(HRUtils.nextFacilitiesSerialNumber()).build();

			workstationRepository.save(workstation);

			workstation = Workstation.builder().vendor("Mac").model("Mac Book Air 13 2012")
					.facilitiesSerialNumber(HRUtils.nextFacilitiesSerialNumber()).build();

			workstationRepository.save(workstation);

			workstation = Workstation.builder().vendor("Mac").model("Mac mini pro")
					.facilitiesSerialNumber(HRUtils.nextFacilitiesSerialNumber()).build();

			workstationRepository.save(workstation);

			Workstation ws = workstationRepository.findById(1L).get();

			ws.setEmployeeId(1L);

			workstationRepository.save(ws);
		};
	}

}
