package com.atl.commerce.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.atl.commerce.entities.Usuario;
import com.atl.commerce.security.config.JwtGeneratorInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Override
	public Map<String, String> gerarToken(Usuario usuario) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(usuario.getLogin()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, "usuarioLogado").claim("idUsuario", usuario.getId()).compact();
		Map<String, String> token = new HashMap<>();
		token.put("tipoUsuario", usuario.getTipoUsuario().getDescricao());
		token.put("token", jwtToken);
		token.put("id", String.valueOf(usuario.getId()));
		return token;
	}

	public String obterUsernameDoToken(String token) {
		return obterClaim(token, Claims::getSubject);
	}

	public Date obterDataDeExpiracao(String token) {
		return obterClaim(token, Claims::getExpiration);
	}

	public <T> T obterClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = obterTodosOsClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims obterTodosOsClaims(String token) {
		return Jwts.parser().setSigningKey("usuarioLogado").parseClaimsJws(token).getBody();
	}

	private Boolean tokenEstaExpirado(String token) {
		final Date expiration = obterDataDeExpiracao(token);
		return expiration.before(new Date());
	}

	public Boolean validarToken(String token, Usuario user) {
		final String username = obterUsernameDoToken(token);
		return (username.equals(user.getLogin()) && !tokenEstaExpirado(token));
	}

}