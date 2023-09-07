package com.atl.commerce.dtos;

import com.atl.commerce.enums.TipoUsuario;

public class UsuarioDTO {

	private int id;
	private String login;
	private String senha;
	private TipoUsuario tipoUsuario;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String login, String senha, TipoUsuario tipoUsuario) {
		super();
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(final TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
