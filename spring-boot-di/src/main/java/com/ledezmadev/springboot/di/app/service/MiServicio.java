package com.ledezmadev.springboot.di.app.service;

import org.springframework.stereotype.Component;


//@Component("miServicioSimlpe")
public class MiServicio implements IServicio{

	@Override
	public String operacion() {
		
		return "ejecutando algun proceso simple...";
		
	}
	
}
