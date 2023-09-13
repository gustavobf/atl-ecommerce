package com.atl.commerce.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.PedidoDTO;
import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.Pedido;
import com.atl.commerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteService clienteService;

	public PedidoDTO getByClienteId(int id) {
		return pedidoToDTO(repository.getByClienteId(id));
	}

	public List<PedidoDTO> getByClienteIdComDataPedido(int id) {
		return repository.getByClienteIdComDataPedido(id).stream().map(this::pedidoToDTO).collect(Collectors.toList());
	}

	public PedidoDTO salvar(PedidoDTO pedido) {
		return pedidoToDTO(repository.save(dtoToPedido(pedido)));
	}

	public PedidoDTO obterPorClienteId(int clienteId) {
		return pedidoToDTO(repository.getByClienteId(clienteId));
	}

	public List<PedidoDTO> obterTodos() {
		return repository.findAll().stream().map(this::pedidoToDTO).collect(Collectors.toList());
	}

	public PedidoDTO finalizarPedido(Cliente cliente) {
		PedidoDTO pedido = obterPorClienteId(cliente.getId());

		if (pedido != null) {
			pedido.setDataPedido(LocalDate.now());
			return salvar(pedido);
		}
		return null;
	}

	PedidoDTO pedidoToDTO(Pedido pedido) {
		if (pedido != null) {
			PedidoDTO dto = new PedidoDTO();
			dto.setId(pedido.getId());
			dto.setDataPedido(pedido.getDataPedido());
			dto.setCliente(clienteService.clienteToDTO(pedido.getCliente()));
			return dto;
		} else {
			return null;
		}
	}

	Pedido dtoToPedido(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setId(dto.getId());
		pedido.setDataPedido(dto.getDataPedido());
		pedido.setCliente(clienteService.dtoToCliente(dto.getCliente()));
		return pedido;
	}

	public int deletarPorId(int id) {
		repository.deleteById(id);
		return id;
	}

	public PedidoDTO atualizar(PedidoDTO dto) {
		return pedidoToDTO(repository.save(dtoToPedido(dto)));
	}

}
