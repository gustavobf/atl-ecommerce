package com.atl.commerce.dtos;

public class ResponseItemPedidoDTO {

	private int idItem;
	private double precoTotal;
	private int quantidade;
	private ResponseProdutoDTO produto;

	public ResponseItemPedidoDTO() {
		super();
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public ResponseProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ResponseProdutoDTO produto) {
		this.produto = produto;
	}

}
