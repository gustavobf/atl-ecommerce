package com.atl.commerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.UsuarioDTO;
import com.atl.commerce.entities.Usuario;
import com.atl.commerce.enums.TipoUsuario;
import com.atl.commerce.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDTO novoUsuario(Usuario user) {
		return usuarioToDto(usuarioRepository.save(user));
	}

	public UsuarioDTO getUsuarioByLoginAndSenha(String login, String senha) throws RuntimeException {
		Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
		return usuario != null ? usuarioToDto(usuario) : null;
	}

	public List<UsuarioDTO> obterTodos() {
		return usuarioRepository.findAll().stream().map(this::usuarioToDto).collect(Collectors.toList());
	}

	public UsuarioDTO obterPorId(int id) {
		return usuarioToDto(usuarioRepository.findById(id).get());
	}
	
	public UsuarioDTO atualizar (UsuarioDTO dto) {
		return usuarioToDto(usuarioRepository.save(dtoToUsuario(dto)));
	}
	
	public int deletarPorID (int id) {
		usuarioRepository.deleteById(id);
		return id;
	}

	public UsuarioDTO novoUsuarioCliente(UsuarioDTO dto) {
		dto.setTipoUsuario(TipoUsuario.CLIENTE);
		return novoUsuario(dtoToUsuario(dto));
	}

	public UsuarioDTO novoUsuarioAdmin(UsuarioDTO dto) {
		dto.setTipoUsuario(TipoUsuario.ADMIN);
		return novoUsuario(dtoToUsuario(dto));
	}

	public UsuarioDTO usuarioToDto(final Usuario usuario) {
		final UsuarioDTO dto = new UsuarioDTO();
		dto.setId(usuario.getId());
		dto.setLogin(usuario.getLogin());
		dto.setSenha(usuario.getSenha());
		dto.setTipoUsuario(usuario.getTipoUsuario());
		return dto;
	}

	public Usuario dtoToUsuario(final UsuarioDTO dto) {
		final Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setLogin(dto.getLogin());
		usuario.setSenha(dto.getSenha());
		usuario.setTipoUsuario(dto.getTipoUsuario());
		return usuario;
	}

}