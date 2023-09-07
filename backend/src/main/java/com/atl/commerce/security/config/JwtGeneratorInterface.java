package com.atl.commerce.security.config;

import java.util.Map;

import com.atl.commerce.entities.Usuario;


public interface JwtGeneratorInterface {
	Map<String, String> gerarToken(Usuario usuario);
}