package com.atl.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.ProdutoDTO;
import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.ItemPedido;
import com.atl.commerce.entities.Pedido;
import com.atl.commerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoService pedidoService;

	public ItemPedido salvar(ItemPedido itemPedido) {
		return repository.save(itemPedido);
	}

	public void adicionarProdutoAPedido(int produtoId, int quantidade, Cliente cliente) {
		ProdutoDTO produto = produtoService.obterPorId(produtoId);

		Pedido pedido = pedidoService.getByClienteId(cliente.getId());

		if (pedido != null) {
			ItemPedido itemPedido = ItemPedido.novo(pedido, quantidade, produtoService.dtoToProduto(produto));
			salvar(itemPedido);
		} else {
			Pedido pedidoNovo = new Pedido();
			pedidoNovo.setCliente(cliente);
			Pedido pedidoSalvo = pedidoService.salvar(pedidoNovo);
			ItemPedido itemPedido = ItemPedido.novo(pedidoSalvo, quantidade, produtoService.dtoToProduto(produto));
			salvar(itemPedido);
		}
	}

}
