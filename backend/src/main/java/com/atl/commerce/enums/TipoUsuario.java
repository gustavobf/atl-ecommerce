package com.atl.commerce.enums;

public enum TipoUsuario {

	CLIENTE(0, "CLIENTE"), ADMIN(1, "ADMIN");

	private int valor;
	private String descricao;

	private TipoUsuario(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public static TipoUsuario fromNumber(int number) {
		switch (number) {
		case 0:
			return TipoUsuario.CLIENTE;

		case 1:
			return TipoUsuario.ADMIN;

		default:
			throw new IllegalArgumentException();
		}
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
