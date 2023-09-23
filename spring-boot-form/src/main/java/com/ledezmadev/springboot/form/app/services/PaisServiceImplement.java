package com.ledezmadev.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ledezmadev.springboot.form.app.models.domains.Pais;

@Service
public class PaisServiceImplement implements PaisService {
	
	private List<Pais> lista;

	public PaisServiceImplement() {
		this.lista = Arrays.asList(new Pais(1, "mx","Mexico"), 
				new Pais(2, "co","Colombia"), 
				new Pais(3, "es","España"));

	}

	@Override
	public List<Pais> listar() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Pais getById(Integer id) {
		// TODO Auto-generated method stub
		Pais resultado = null;
		for(Pais pais: this.lista) {
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
