package com.ledezmadev.springboot.error.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ledezmadev.springboot.error.app.errors.UsuarioNotFoundException;
import com.ledezmadev.springboot.error.app.models.domain.Usuario;
import com.ledezmadev.springboot.error.app.services.UsuarioService;

@Controller
public class AppController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/")
	public String index() {
		
		Integer valor = Integer.parseInt("10xx");
		
		return "index";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Integer id, Model model) {
		
//		Usuario usuario = usuarioService.getById(id);
		
		Usuario usuario = usuarioService.getByIdOptional(id).orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
		
		model.addAttribute("usuario", usuario);		
		
		return "ver";
	}
}
