package com.atl.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.dtos.CategoriaDTO;
import com.atl.commerce.security.services.JwtGeneratorService;
import com.atl.commerce.services.CategoriaService;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	JwtGeneratorService jwt;

	@GetMapping
	public List<CategoriaDTO> obterTodos() {
		return categoriaService.obterTodos();
	}

}
