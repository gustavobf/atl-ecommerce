package com.atl.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	JwtGeneratorImpl jwt;

	@PostMapping
	public ProdutoDTO novo(@RequestBody ProdutoDTO dto) {
		return service.novo(dto);
	}

	@GetMapping
	public List<ProdutoDTO> obterTodos() {
		return service.obterTodos();
	}

	@PutMapping()
	public ProdutoDTO atualizar(@RequestBody ProdutoDTO dto) {
		return service.atualizar(dto);
	}

	@GetMapping("/{id}")
	public ProdutoDTO obterTodos(@PathVariable int id) {
		return service.obterPorId(id);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable int id) {
		service.deletarPorId(id);
	}

}
