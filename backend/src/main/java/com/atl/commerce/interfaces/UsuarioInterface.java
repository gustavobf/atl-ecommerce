package com.atl.commerce.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atl.commerce.entities.Usuario;

@Service
public interface UsuarioInterface {
	public Usuario getUsuarioByLoginAndSenha(String login, String senha) throws RuntimeException;

	void saveUsuario(Usuario user);

	public List<Usuario> getAll();
}