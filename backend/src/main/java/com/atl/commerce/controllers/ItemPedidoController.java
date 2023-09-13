package com.atl.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.dtos.ResponsePedidoDTO;
import com.atl.commerce.security.services.JwtGeneratorService;
import com.atl.commerce.services.ItemPedidoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/itempedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private JwtGeneratorService jwt;

	@PostMapping("/adicionar/{produtoid}")
	public void adicionarPedido(@PathVariable("produtoid") int produtoId, @RequestParam int quantidade,
			HttpServletRequest request) {
		itemPedidoService.adicionarProdutoAPedido(produtoId, quantidade,
				jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));
	}

	@PostMapping("/atualizar")
	public ResponseEntity<?> atualizarPedido(@RequestParam int produtoid, @RequestParam int quantidade,
			HttpServletRequest request) {
		Integer idPedido = itemPedidoService.atualizarProdutoDoPedido(produtoid, quantidade,
				jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));
		return idPedido == null ? new ResponseEntity<>(null, HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/obterpedidos")
	public ResponseEntity<?> obterPedidos(HttpServletRequest request) {
		ResponsePedidoDTO response = itemPedidoService
				.obterPedido(jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));

		return response == null ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(response, HttpStatus.OK);
	}

}
