package com.ledezmadev.springboot.form.app.services;

import java.util.List;

import com.ledezmadev.springboot.form.app.models.domains.Pais;

public interface PaisService {

	public List<Pais> listar();
	public Pais getById(Integer id);
	
}
