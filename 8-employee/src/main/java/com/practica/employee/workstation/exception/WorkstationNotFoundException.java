package com.practica.employee.workstation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WorkstationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580653048106041346L;
	
	public WorkstationNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
