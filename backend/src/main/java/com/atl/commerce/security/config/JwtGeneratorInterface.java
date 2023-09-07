package com.atl.commerce.security.config;

import java.util.Map;

import com.atl.commerce.dtos.UsuarioDTO;


public interface JwtGeneratorInterface {
	Map<String, String> gerarToken(UsuarioDTO usuario);
}