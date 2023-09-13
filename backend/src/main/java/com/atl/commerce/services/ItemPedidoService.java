package com.atl.commerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.PedidoDTO;
import com.atl.commerce.dtos.ProdutoDTO;
import com.atl.commerce.dtos.ResponseItemPedidoDTO;
import com.atl.commerce.dtos.ResponsePedidoDTO;
import com.atl.commerce.dtos.ResponseProdutoDTO;
import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.ItemPedido;
import com.atl.commerce.entities.Produto;
import com.atl.commerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ClienteService clienteService;

	public ItemPedido salvar(ItemPedido itemPedido) {
		return repository.save(itemPedido);
	}

	public ResponsePedidoDTO obterPedido(Cliente cliente) {
		return pedidoToResponsePedido(pedidoService.getByClienteId(cliente.getId()));
	}

	public List<ResponsePedidoDTO> obterPedidoHistorico(Cliente cliente) {
		return pedidoService.getByClienteIdComDataPedido(cliente.getId()).stream().map(this::pedidoToResponsePedido)
				.collect(Collectors.toList());
	}

	private ResponsePedidoDTO pedidoToResponsePedido(PedidoDTO pedido) {
		if (pedido != null) {
			ResponsePedidoDTO responsePedidoDto = new ResponsePedidoDTO();
			responsePedidoDto.setIdPedido(pedido.getId());
			responsePedidoDto.setDataPedido(pedido.getDataPedido());
			responsePedidoDto.setItemPedido(obterResponseItemPedidoByPedidoId(pedido));
			return responsePedidoDto;
		} else {
			return null;
		}
	}

	private List<ResponseItemPedidoDTO> obterResponseItemPedidoByPedidoId(PedidoDTO pedido) {
		return obterByPedidoId(pedido.getId()).stream().map(item -> {
			ResponseItemPedidoDTO responseItemPedidoDTO = new ResponseItemPedidoDTO();
			responseItemPedidoDTO.setIdItem(item.getId());
			responseItemPedidoDTO.setPrecoTotal(item.getPrecoTotal());
			responseItemPedidoDTO.setQuantidade(item.getQuantidade());
			responseItemPedidoDTO.setProduto(
					produtoToResponseProduto(obterProdutoByItemPedidoId(responseItemPedidoDTO.getIdItem())));
			return responseItemPedidoDTO;
		}).collect(Collectors.toList());
	}

	private ResponseProdutoDTO produtoToResponseProduto(Produto produto) {
		ResponseProdutoDTO responseProdutoDTO = new ResponseProdutoDTO();
		responseProdutoDTO.setIdProduto(produto.getId());
		responseProdutoDTO.setImagem(produto.getImagem());
		responseProdutoDTO.setNome(produto.getNome());
		responseProdutoDTO.setPreco(produto.getPreco());
		responseProdutoDTO.setDescricao(produto.getDescricao());
		responseProdutoDTO.setCategoria(produto.getCategoria().getNome());
		return responseProdutoDTO;
	}

	public void adicionarProdutoAPedido(int produtoId, int quantidade, Cliente cliente) {
		ProdutoDTO produto = produtoService.obterPorId(produtoId);

		PedidoDTO pedido = pedidoService.getByClienteId(cliente.getId());

		if (pedido != null) {
			ItemPedido itemPedido = ItemPedido.novo(pedidoService.dtoToPedido(pedido), quantidade,
					produtoService.dtoToProduto(produto));
			salvar(itemPedido);
		} else {
			PedidoDTO pedidoNovo = new PedidoDTO();
			pedidoNovo.setCliente(clienteService.clienteToDTO(cliente));
			PedidoDTO pedidoSalvo = pedidoService.salvar(pedidoNovo);
			ItemPedido itemPedido = ItemPedido.novo(pedidoService.dtoToPedido(pedidoSalvo), quantidade,
					produtoService.dtoToProduto(produto));
			salvar(itemPedido);
		}
	}

	public Integer atualizarProdutoDoPedido(int produtoId, int quantidade, Cliente cliente) {

		ProdutoDTO produto = produtoService.obterPorId(produtoId);

		PedidoDTO pedido = pedidoService.getByClienteId(cliente.getId());

		if (pedido != null) {
			List<ItemPedido> itens = obterByPedidoId(pedido.getId());

			itens.stream().map(item -> {
				if (item.getProduto().getId() == produto.getId()) {
					if (quantidade == 0) {
						repository.delete(item);
					} else {
						item.setQuantidade(quantidade);
						item.setPrecoTotal(quantidade * item.getProduto().getPreco());
						repository.save(item);
					}
				}
				return item;
			}).collect(Collectors.toList());
			return pedido.getId();
		} else {
			return null;
		}
	}

	private List<ItemPedido> obterByPedidoId(int pedidoId) {
		return repository.getByPedidoId(pedidoId);
	}

	private Produto obterProdutoByItemPedidoId(int pedidoId) {
		return repository.getProdutosByItemPedidoId(pedidoId);
	}

}
