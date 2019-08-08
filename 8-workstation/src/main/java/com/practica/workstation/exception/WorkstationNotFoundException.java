package com.practica.workstation.exception;

public class WorkstationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984595138057458810L;
	
	public WorkstationNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
