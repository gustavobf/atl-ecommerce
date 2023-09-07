package com.atl.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.dtos.LoginDTO;
import com.atl.commerce.dtos.UsuarioDTO;
import com.atl.commerce.security.services.JwtGeneratorService;
import com.atl.commerce.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtGeneratorService jwtService;

	@PostMapping("/novo")
	public ResponseEntity<?> novoUsuario(@RequestBody UsuarioDTO dto) {
		try {
			usuarioService.novoUsuarioCliente(dto);
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO login) {
		try {
			if (login.getLogin() == null || login.getSenha() == null) {
				throw new RuntimeException("Usuário ou Senha estão vazios.");
			}
			UsuarioDTO usuarioLogado = usuarioService.getUsuarioByLoginAndSenha(login.getLogin(), login.getSenha());
			if (usuarioLogado == null) {
				throw new RuntimeException("Usuário ou Senha Inválidos.");
			}
			return new ResponseEntity<>(jwtService.gerarToken(usuarioLogado), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}