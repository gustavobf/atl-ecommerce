package com.atl.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.commerce.entities.Usuario;
import com.atl.commerce.security.config.JwtGeneratorInterface;
import com.atl.commerce.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtGeneratorInterface jwtGenerator;

	@PostMapping("/novo")
	public ResponseEntity<?> novoUsuario(@RequestBody Usuario usuario) {
		try {
			usuarioService.novoUsuarioCliente(usuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		try {
			if (usuario.getLogin() == null || usuario.getSenha() == null) {
				throw new RuntimeException("Usuário ou Senha estão vazios.");
			}
			Usuario usuarioLogado = usuarioService.getUsuarioByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
			if (usuarioLogado == null) {
				throw new RuntimeException("Usuário ou Senha Inválidos.");
			}
			return new ResponseEntity<>(jwtGenerator.gerarToken(usuarioLogado), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}