package com.atl.commerce.dtos;

import java.time.LocalDate;
import java.util.List;

public class ResponsePedidoDTO {

	private int idPedido;
	private LocalDate dataPedido;
	private List<ResponseItemPedidoDTO> itemPedido;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public List<ResponseItemPedidoDTO> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ResponseItemPedidoDTO> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

}
