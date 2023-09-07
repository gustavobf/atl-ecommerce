package com.atl.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.UsuarioDTO;
import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.Usuario;
import com.atl.commerce.repositories.ClienteRepository;

@Service
public class ClienteService{

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	UsuarioService usuarioService;

	public Cliente obterByUsuario(UsuarioDTO usuarioDTO) {
		return repository.findByUsuario(usuarioService.dtoToUsuario(usuarioDTO));
	}

	public Cliente novoCliente(Cliente cliente) {
		return repository.save(cliente);
	}

}
