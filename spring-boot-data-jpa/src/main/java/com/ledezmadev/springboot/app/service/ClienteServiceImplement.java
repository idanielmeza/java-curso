package com.ledezmadev.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledezmadev.springboot.app.models.dao.IClienteDAO;
import com.ledezmadev.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImplement implements IClienteService{
	
	@Autowired
	private IClienteDAO clienteDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDAO.deleteById(id);
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		
		return clienteDAO.findAll(pageable);
	}
	
}
