package com.ledezmadev.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ledezmadev.springboot.form.app.models.domains.Role;

@Service
public interface RoleService {

	public List<Role> listar();
	
	public Role obtenerPorId(Integer id);
	
}
