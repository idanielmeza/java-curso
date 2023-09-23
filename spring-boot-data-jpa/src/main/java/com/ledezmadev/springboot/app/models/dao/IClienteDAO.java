package com.ledezmadev.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledezmadev.springboot.app.models.entity.Cliente;

public interface IClienteDAO  extends JpaRepository<Cliente, Long>{
	
}

