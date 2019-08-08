package com.practica.employee.workstation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WorkstationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 691624661958289463L;
	
	public WorkstationException(String mensaje) {
		super(mensaje);
	}

}
