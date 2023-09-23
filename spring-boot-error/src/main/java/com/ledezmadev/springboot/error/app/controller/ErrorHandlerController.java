package com.ledezmadev.springboot.error.app.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ledezmadev.springboot.error.app.errors.UsuarioNotFoundException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticError(ArithmeticException ex, Model model) {
		
		model.addAttribute("error", "Error aritmetico");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/arithmetic";
	}

	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatError(NumberFormatException ex, Model model) {
		model.addAttribute("error", "Error al formatear numero");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/arithmetic";
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public String userNotFoundError(UsuarioNotFoundException ex, Model model) {
		model.addAttribute("error", "Usuario no encontrado");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/arithmetic";
	}
	
}
