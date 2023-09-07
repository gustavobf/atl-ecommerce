package com.atl.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.entities.Usuario;
import com.atl.commerce.enums.TipoUsuario;
import com.atl.commerce.interfaces.UsuarioInterface;
import com.atl.commerce.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UsuarioInterface {
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository userRepository) {
		usuarioRepository = userRepository;
	}

	@Override
	public void saveUsuario(Usuario user) {
		usuarioRepository.save(user);
	}

	@Override
	public Usuario getUsuarioByLoginAndSenha(String login, String senha) throws RuntimeException {
		Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
		if (usuario == null) {
			throw new RuntimeException("Usuário e Senha Inválidos.");
		}
		return usuario;
	}

	@Override
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public void novoUsuarioCliente(Usuario usuario) {
		usuario.setTipoUsuario(TipoUsuario.CLIENTE);
		saveUsuario(usuario);
	}

	public void novoUsuarioAdmin(Usuario usuario) {
		usuario.setTipoUsuario(TipoUsuario.ADMIN);
		saveUsuario(usuario);
	}

}