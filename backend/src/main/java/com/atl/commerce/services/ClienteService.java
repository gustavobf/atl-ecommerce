package com.atl.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.Usuario;
import com.atl.commerce.repositories.ClienteRepository;

@Service
public class ClienteService{

	@Autowired
	ClienteRepository repository;

	public Cliente obterByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

}
