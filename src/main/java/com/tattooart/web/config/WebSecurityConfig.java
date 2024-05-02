package com.tattooart.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
	
	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }
	
	@Bean
	@SuppressWarnings(value = "all")
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
		
		return http
				.cors()
				.and()
				.csrf().disable()	
				.authorizeHttpRequests()

				//LOGIN
				.requestMatchers("/auth/login").permitAll()
				.requestMatchers(HttpMethod.POST, "/users/**").permitAll()
				
//				//USER
//				.requestMatchers(HttpMethod.GET, "/tattooists").hasRole("USER")
//				.requestMatchers(HttpMethod.GET, "/tattoolists").hasRole("USER")
				
				//ADMIN
				//users
//				.requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//				
//				//tattooists
//				.requestMatchers(HttpMethod.GET, "/tattooists/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/tattooists/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/tattooists/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/tattooists/**").hasRole("ADMIN")
//				
//				//tattoolists
//				.requestMatchers(HttpMethod.GET, "/tattoolists/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/tattoolists/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/tattoolists/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/tattoolists/**").hasRole("ADMIN")
//				
//				//appointments
//				.requestMatchers(HttpMethod.GET, "/appointments/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/appointments/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/appointments/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/appointments/**").hasRole("ADMIN")
				
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	 
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}