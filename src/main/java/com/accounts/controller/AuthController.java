package com.accounts.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.entity.AccountEntity;
import com.accounts.service.AccountService;
import com.accounts.service.AuthorizationService;

@RestController
@RequestMapping("/Auth")
public class AuthController {

	@Autowired
	AuthorizationService auth;
	
	@Autowired
	AccountService service;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager manager;
	
	@GetMapping("/sign")
	public ResponseEntity<Map<String, Object> > sign(@RequestParam String name, @RequestParam String password, @RequestParam String rol) throws Exception{
		
		AccountEntity accountUser = new AccountEntity();
		
		accountUser.setName(name);
		accountUser.setPassword(passwordEncoder.encode(password));
		accountUser.setRol(rol);
		accountUser = service.crearAccount(accountUser, true);
		
		Map<String, Object> respose = new HashMap<String, Object>();
		
		respose.put("User", accountUser);
		return new ResponseEntity<Map<String, Object> >(respose, HttpStatus.CREATED);// HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/login")
	public ResponseEntity<Map<String, Object> > login(@RequestParam String name, @RequestParam String password) {
		AccountEntity accountUser = null;
//		boolean isAuthenticated = auth.authenticate(name, password);
		manager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
//		String token = null;
		accountUser = service.getAccount(name);
		String token = auth.generateToken(accountUser);
		
		Map<String, Object> respose = new HashMap<String, Object>();
		respose.put("token", token);
		System.out.println("token: "+token);
		System.out.println(passwordEncoder.encode(password));
		
		return new ResponseEntity<Map<String, Object> >(respose, HttpStatus.OK);
		
	}	
	
}
