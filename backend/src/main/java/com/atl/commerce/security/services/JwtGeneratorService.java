package com.atl.commerce.security.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.UsuarioDTO;
import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.Usuario;
import com.atl.commerce.enums.TipoUsuario;
import com.atl.commerce.services.ClienteService;
import com.atl.commerce.services.UsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtGeneratorService {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Autowired
	ClienteService clienteService;

	@Autowired
	UsuarioService usuarioService;

	public Map<String, String> gerarToken(UsuarioDTO usuario) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(clienteService.obterByUsuario(usuarioService.dtoTousuario(
				usuario)).getNome()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, "usuarioLogado").claim("id", usuario.getId())
				.claim("tipo", usuario.getTipoUsuario().getValor()).claim("login", usuario.getLogin())
				.compact();
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

	public String obterTokenDaRequisicao(HttpServletRequest request) {
		return request == null ? "" : request.getHeader("authorization").replace("Bearer ", "").trim();
	}

	public boolean isAdmin(String token) {
		return TipoUsuario.ADMIN.equals(TipoUsuario.fromNumber((int) obterTodosOsClaims(token).get("tipo")));
	}

	public boolean isCliente(String token) {
		return TipoUsuario.CLIENTE.equals(TipoUsuario.fromNumber((int) obterTodosOsClaims(token).get("tipo")));
	}

	public Cliente obterClientePorToken(String token) {
		return clienteService.obterByUsuario(usuarioService.obterPorId((int) obterTodosOsClaims(token).get("id")));
	}

}