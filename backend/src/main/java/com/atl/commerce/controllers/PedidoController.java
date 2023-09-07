package com.atl.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.entities.Pedido;
import com.atl.commerce.security.services.JwtGeneratorService;
import com.atl.commerce.services.PedidoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private JwtGeneratorService jwt;

	@PostMapping("/finalizar/{pedidoid}")
	public ResponseEntity<?> finalizarPedido(@PathVariable("pedidoid") int pedidoid, HttpServletRequest request) {
		try {
			Pedido pedidoFinalizado = pedidoService.finalizarPedido(pedidoid, jwt.obterClientePorToken(jwt.obterTokenDaRequisicao(request)));
			return new ResponseEntity<>(pedidoFinalizado, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

}
