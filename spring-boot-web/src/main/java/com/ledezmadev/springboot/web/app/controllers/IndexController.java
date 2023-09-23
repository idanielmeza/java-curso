package com.ledezmadev.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ledezmadev.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {

	@Value("${texto.indexController.index.titulo}")
	private String textoIndex;
	@Value("${texto.indexController.perfil.titulo}")
	private String textoPerfil;
	@Value("${texto.indexController.listar.titulo}")
	private String textoListar;
	
	@GetMapping({"/index", "/", "/home"})
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Daniel");
		usuario.setApellido("Ledemza");
		usuario.setEmail("mawi@mawi.es");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", textoListar);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		
		usuarios.add(new Usuario("Oscar", "Matias", "mawi@mawi.es"));
		usuarios.add(new Usuario("Daniel", "Meza", "dany@mawi.es"));
		usuarios.add(new Usuario("Oscar", "Daniel", "mawi2@mawi.es"));
		
		return usuarios;
	}
}
