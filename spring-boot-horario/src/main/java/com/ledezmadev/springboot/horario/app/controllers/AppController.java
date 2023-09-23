package com.ledezmadev.springboot.horario.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {

	@GetMapping({"/", "/index", ""})
	public String index(Model model) {
	
		model.addAttribute("titulo", "Biendenido al horario de atencion a clientes");
		return "index";
	}
	
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
	
		model.addAttribute("titulo", "Lo sentimos, fuera de horario.");
		model.addAttribute("mensaje", "Lo sentimos estamos fuera del horario de atencion a clientes");
		return "cerrado";
	}
	
}
