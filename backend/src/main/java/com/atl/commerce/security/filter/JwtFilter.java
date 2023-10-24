package com.atl.commerce.security.filter;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "*");

		if (!"OPTIONS".equals(request.getMethod())) {

			String path = request.getRequestURI();
			if (path.equals("/api/usuario/login") || path.startsWith("/h2-console") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
				filterChain.doFilter(request, response);
				return;
			}

			String authHeader = request.getHeader("Authorization");
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Autorização necessária.");
				return;
			}

			final String token = authHeader.substring(7);
			try {
				Claims claims = Jwts.parser().setSigningKey("usuarioLogado").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Inválido.");
				return;
			}
			filterChain.doFilter(request, response);
		}

	}
}