package com.atl.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.dtos.ProdutoDTO;
import com.atl.commerce.security.service.JwtGeneratorImpl;
import com.atl.commerce.services.ProdutoService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	JwtGeneratorImpl jwt;

	@PostMapping
	public ResponseEntity<?> novo(@RequestBody ProdutoDTO dto, HttpServletRequest request) {
		return jwt.isAdmin(jwt.obterTokenDaRequisicao(request))
				? new ResponseEntity<>(service.novo(dto), HttpStatus.CREATED)
						: new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping
	public Claims obterTodos(HttpServletRequest request) {
		return jwt.obterTodosOsClaims(jwt.obterTokenDaRequisicao(request));
	}
	//	@GetMapping
	//	public List<ProdutoDTO> obterTodos() {
	//		return service.obterTodos();
	//	}

	@PutMapping()
	public ResponseEntity<?> atualizar(@RequestBody ProdutoDTO dto, HttpServletRequest request) {
		return jwt.isAdmin(jwt.obterTokenDaRequisicao(request))
				? new ResponseEntity<>(service.atualizar(dto), HttpStatus.CREATED)
						: new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/{id}")
	public ProdutoDTO obterTodos(@PathVariable int id) {
		return service.obterPorId(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable int id, HttpServletRequest request) {
		return jwt.isAdmin(jwt.obterTokenDaRequisicao(request))
				? new ResponseEntity<>(service.deletarPorId(id), HttpStatus.NO_CONTENT)
						: new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

}
