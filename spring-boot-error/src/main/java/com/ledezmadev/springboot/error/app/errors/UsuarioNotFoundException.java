package com.ledezmadev.springboot.error.app.errors;

public class UsuarioNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(String id) {
		super("Usuario id: ".concat(id).concat(" no existe"));
	}

	
	
}
