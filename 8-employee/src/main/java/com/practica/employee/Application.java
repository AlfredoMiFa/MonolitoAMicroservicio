package com.practica.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.practica.employee.exception.model.Employee;
import com.practica.employee.repository.EmployeeRepository;
import com.practica.employee.utils.HRUtils;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	private EmployeeRepository employeeRepository;
	
	public Application(EmployeeRepository employee) {
		this.employeeRepository=employee;
	}
	
	@Bean
	public CommandLineRunner startup() {

		return (args) -> {
			Employee employee = Employee.builder().firstName("Alfredo").lastName("Miranda")
					.employeeNumber(HRUtils.nextEmployeeNumber()).build();

			System.out.println(employee);

			employeeRepository.save(employee);

			employee = Employee.builder().firstName("Pedrito").lastName("Perez")
					.employeeNumber(HRUtils.nextEmployeeNumber()).build();

			System.out.println(employee);

			employeeRepository.save(employee);

		};
	}

}
