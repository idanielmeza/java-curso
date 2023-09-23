package com.ledezmadev.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ledezmadev.springboot.form.app.models.domains.Role;

@Service
public class RoleServiceImpl implements RoleService{
	
	private List<Role> roles;

	public RoleServiceImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "SuperAdmin", "ROLE_SUPERADMIN"));
		this.roles.add(new Role(3, "Administrador", "ROLE_MANAGER"));
	}

	@Override
	public List<Role> listar() {
		return this.roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		
		Role resultado = null;
		
		for(Role role: roles ) {
			if(id == role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}

	
	
}
