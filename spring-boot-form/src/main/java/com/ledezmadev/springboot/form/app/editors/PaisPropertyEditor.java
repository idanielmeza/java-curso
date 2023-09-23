package com.ledezmadev.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ledezmadev.springboot.form.app.services.PaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {
	
	@Autowired
	private PaisService service;

	@Override
	public void setAsText(String ID) throws IllegalArgumentException {
		
		if(ID != null && ID.length() > 0) {
			try {
				Integer id = Integer.parseInt(ID);
				this.setValue(service.getById(id));
			}catch(NumberFormatException e) {
				setValue(null);
			}
		}else {
			setValue(null);
		}
	}

}
