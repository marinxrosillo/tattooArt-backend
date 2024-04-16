package com.tattooart.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	@SuppressWarnings(value = "all")
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			
		http
			.csrf().disable() //CSRF disable
			.cors()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests() //peticiones http
			
			//LOGIN
			.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
			
			//USER
			.requestMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
			.requestMatchers(HttpMethod.GET, "/appointments/**").hasRole("USER")
			.requestMatchers(HttpMethod.GET, "/tattoists/**").hasRole("USER")
			.requestMatchers(HttpMethod.GET, "/tattoolists/**").hasRole("USER")
//			
//			//ADMIN
//			//categories
//			.requestMatchers(HttpMethod.GET, "/categories/**").hasRole("ADMIN")
			
			.anyRequest() //cualquier peticion
			.authenticated()
			.and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}