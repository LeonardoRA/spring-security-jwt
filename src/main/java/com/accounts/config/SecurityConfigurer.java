package com.accounts.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.accounts.repository.AccountRepository;
import com.accounts.service.AccountService;

//import com.accounts.configs.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfigurer {
	
	 @Autowired
	 private JwtAuthenticationFilter jwtAuthenticationFilter;
	 @Autowired
	 private AuthenticationProvider authenticationProvider;
	 
	@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 
		 http.authorizeHttpRequests((requests) -> requests
				 .requestMatchers(HttpMethod.GET, "/Auth/**").permitAll()
				 .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
				 .requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
				 .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()
				 .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
				 ).authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		 			
			return http.build();
	 }
	 
	 
	 
}
