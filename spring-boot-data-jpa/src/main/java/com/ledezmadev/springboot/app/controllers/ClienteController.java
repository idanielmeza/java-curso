package com.ledezmadev.springboot.app.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ledezmadev.springboot.app.models.entity.Cliente;
import com.ledezmadev.springboot.app.service.IClienteService;
import com.ledezmadev.springboot.app.service.IUploadFileService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	@GetMapping(value="/uploads/{}filename:.+")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		
		Resource recurso = uploadFileService.load(filename);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+ recurso.getFilename() +"\"").body(recurso);
	}
	
	@RequestMapping(value="/ver/{id}")
	public String verUsuario(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente = clienteService.findOne(id);
		
		if(cliente == null) {
			return "reditect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Informacion Cliente:".concat(cliente.getNombre()).concat(" ").concat(cliente.getApellido()));
		
		return "ver";
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
	 	model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario editar cliente");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile photo ) {
		
		if(result.hasErrors()) {
			System.out.println(result.getFieldErrorCount());
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
		
		if(!photo.isEmpty()) {
			String name = uploadFileService.copy(photo);
			cliente.setPhoto(name);
		}
		
		clienteService.save(cliente);
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		
		if(id > 0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			uploadFileService.delete(cliente.getPhoto());
		}
		
		return "redirect:/listar";
	}
	
	
}
