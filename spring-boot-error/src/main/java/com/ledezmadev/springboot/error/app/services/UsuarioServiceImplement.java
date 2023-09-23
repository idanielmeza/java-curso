package com.ledezmadev.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ledezmadev.springboot.error.app.errors.UsuarioNotFoundException;
import com.ledezmadev.springboot.error.app.models.domain.Usuario;

@Service
public class UsuarioServiceImplement implements UsuarioService {

	private List<Usuario> lista;
	
	
	
	
	public UsuarioServiceImplement() {
		this.lista = new ArrayList<Usuario>();
		this.lista.add( new Usuario(1, "Daniel", "Ledezma"));
		this.lista.add( new Usuario(2, "Maredith", "Martinez"));
		this.lista.add( new Usuario(3, "Pocoyo", "Comecaca"));
	}

	@Override
	public List<Usuario> listar() {
		return this.listar();
	}

	@Override
	public Usuario getById(Integer id) {
		
		Usuario resultado = null;
		
		for(Usuario u: this.lista) {
			if(u.getId().equals(id)) {
				resultado = u;
				break;
			}
		}
		
		if(resultado == null) {
			throw new UsuarioNotFoundException(id.toString());
		}
		
		return resultado;
	}
	
	@Override
	public Optional<Usuario> getByIdOptional(Integer id) {
		
		Usuario usuario = this.getById(id);
		
		return Optional.ofNullable(usuario);

	}

}
