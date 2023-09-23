package com.ledezmadev.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ledezmadev.springboot.form.app.services.RoleService;

@Component
public class RolesEditors extends PropertyEditorSupport{

	@Autowired
	private RoleService service;
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		try {
			
			Integer id = Integer.parseInt(text);
			setValue(service.obtenerPorId(id));
			
		}catch(NumberFormatException e){
			setValue(null);
		}
		
	}

	
	
}
