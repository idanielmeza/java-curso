package com.ledezmadev.springboot.form.app.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ledezmadev.springboot.form.app.editors.NombreMayusculaEditor;
import com.ledezmadev.springboot.form.app.editors.PaisPropertyEditor;
import com.ledezmadev.springboot.form.app.editors.RolesEditors;
import com.ledezmadev.springboot.form.app.models.domains.Pais;
import com.ledezmadev.springboot.form.app.models.domains.Role;
import com.ledezmadev.springboot.form.app.models.domains.Usuario;
import com.ledezmadev.springboot.form.app.services.PaisService;
import com.ledezmadev.springboot.form.app.services.RoleService;
import com.ledezmadev.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;
	@Autowired 
	private PaisService paisService;
	@Autowired
	private PaisPropertyEditor paisEditor;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolesEditors roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(validador);
		SimpleDateFormat dateFormart = new SimpleDateFormat("yyyy-MM-dd");
		dateFormart.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento"  ,new CustomDateEditor(dateFormart, false));
		
		binder.registerCustomEditor(String.class,"nombre" ,new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class,"apellido" ,new NombreMayusculaEditor());
		
		binder.registerCustomEditor(Pais.class,"pais" ,paisEditor);
		
		binder.registerCustomEditor(Role.class,"role" , roleEditor);
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setHabilitar(true); 
		String uuid = UUID.randomUUID().toString();
		usuario.setIdentificador(uuid);
		System.out.println(uuid);
		
		model.addAttribute("usuario", usuario);
		
		return "form";
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paises(){
		Map<String,String> paises = new HashMap<String, String>();
		paises.put("es", "España");
		paises.put("mx", "Mexico");
		paises.put("co", "Colombia");
		return paises;
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		 
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_SUPERADMIN");
		roles.add("ROLE_USER");
		
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap(){
		
		Map<String,String> listaRolesMap = new HashMap<String, String>();
		listaRolesMap.put("ROLE_ADMIN", "Administrador");
		listaRolesMap.put("ROLE_SUPERADMIN", "Super Administrador");
		listaRolesMap.put("ROLE_USER", "Moderador");
		return listaRolesMap;
		
	}
	
	@PostMapping("/form")
	public String postForm(@Valid Usuario usuario, BindingResult result 
//			@RequestParam String username, @RequestParam String password, @RequestParam String email
			) {
//		validador.validate(usuario,result);
		if(result.hasErrors()) {
			System.out.println("Hubo erroes");
			System.out.println(result.getAllErrors());
			
//			Map<String, String> errores = new HashMap<>();
//			
//			result.getFieldErrors().forEach(err -> {
//				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});
//			
//			model.addAttribute("error", errores);
			return "form";
			
		};
		
//		Usuario usuario = new Usuario();
//		
//		usuario.setUsername(username);
//		usuario.setEmail(email);
//		usuario.setPassword(password);
		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required=false) Usuario usuario, Model model, SessionStatus status) {
		
		if(usuario == null) {
			return "redirect:/form";
		}
		
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "postForm";
	}
	
}
