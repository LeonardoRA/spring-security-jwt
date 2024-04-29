package com.accounts.config;

import java.io.IOException;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.accounts.service.AccountService;
import com.accounts.service.AuthorizationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtils utils;
	
	@Autowired
	UserDetailsService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationString = request.getHeader("Authorization");
		
		
		String name = null;
		if (authorizationString != null)
			name = utils.getName(authorizationString.substring(7));
		
		System.out.println("user logged: "+name);
		
		if(name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = service.loadUserByUsername(name);
			
			
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
					userDetails, null,
					userDetails.getAuthorities());
			userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(userToken);
		}
		
		filterChain.doFilter(request, response); 
		
	}


}
