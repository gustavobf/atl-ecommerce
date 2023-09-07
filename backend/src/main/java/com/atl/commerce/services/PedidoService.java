package com.atl.commerce.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.Pedido;
import com.atl.commerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido getByClienteId(int id) {
		return repository.getByClienteId(id);
	}

	public Pedido salvar(Pedido pedido) {
		return repository.save(pedido);
	}

	public Pedido obterPorId(int pedidoId) {
		return repository.findById(pedidoId).get();
	}

	public Pedido finalizarPedido(int pedidoid, Cliente cliente) {
		Pedido pedido = obterPorId(pedidoid);

		if (cliente.getId() == pedido.getCliente().getId()) {
			pedido.setDataPedido(LocalDate.now());
			return salvar(pedido);
		} else {
			throw new RuntimeException();
		}
	}

}
