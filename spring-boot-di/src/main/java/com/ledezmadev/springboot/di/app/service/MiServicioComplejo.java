package com.ledezmadev.springboot.di.app.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


//@Component("miServicioComplejo")
//@Primary
public class MiServicioComplejo implements IServicio{

	@Override
	public String operacion() {
		
		return "ejecutando algun proceso complejo...";
		
	}
	
}
