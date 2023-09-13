package com.atl.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.dtos.PedidoDTO;
import com.atl.commerce.dtos.ResponsePedidoDTO;
import com.atl.commerce.security.services.JwtGeneratorService;
import com.atl.commerce.services.ItemPedidoService;
import com.atl.commerce.services.PedidoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private JwtGeneratorService jwt;

	@PostMapping("/finalizar")
	public ResponseEntity<?> finalizarPedido(HttpServletRequest request) {
		PedidoDTO pedidoFinalizado = pedidoService
				.finalizarPedido(jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));

		return pedidoFinalizado == null ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(pedidoFinalizado, HttpStatus.OK);

	}

	@GetMapping("/obterhistorico")
	public ResponseEntity<?> obterHistorico(HttpServletRequest request) {
		List<ResponsePedidoDTO> listaPedidos = itemPedidoService
				.obterPedidoHistorico(jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));

		return listaPedidos.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(listaPedidos, HttpStatus.OK);

	}

}
