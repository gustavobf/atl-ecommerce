package com.atl.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public void adicionarProduto(@PathVariable("produtoid") int produtoId, @RequestParam int quantidade,
			HttpServletRequest request) {
		itemPedidoService.adicionarProdutoAPedido(produtoId, quantidade,
				jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));
	}

}
