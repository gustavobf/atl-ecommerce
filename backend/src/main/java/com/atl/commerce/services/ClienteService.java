package com.atl.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.ClienteDTO;
import com.atl.commerce.dtos.UsuarioDTO;
import com.atl.commerce.entities.Cliente;
import com.atl.commerce.repositories.ClienteRepository;

@Service
public class ClienteService {

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

	public ClienteDTO atualizar(ClienteDTO dto) {
		return clienteToDTO(repository.save(dtoToCliente(dto)));
	}

	private ClienteDTO clienteToDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setUsuario(usuarioService.usuarioToDto(cliente.getUsuario()));
		return clienteDTO;
	}

	private Cliente dtoToCliente(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setEmail(dto.getEmail());
		cliente.setNome(dto.getNome());
		cliente.setUsuario(usuarioService.dtoToUsuario(dto.getUsuario()));
		return cliente;
	}
}
