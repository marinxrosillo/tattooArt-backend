package com.tattooart.web.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired	
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Validar que sea un Header Auth valido
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Validar un JWT valido
		String jwt = authHeader.split(" ")[1].trim();

		if (!this.jwtUtils.isValid(jwt)) {
			filterChain.doFilter(request, response);
			return;
		}

		// Cargar el usuario del UserDetailsService
		String username = this.jwtUtils.getUsername(jwt);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		// Cargar al usuario en el contexto de seguridad
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
				user.getPassword(), user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request, response);
	}
}
