package com.accounts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.entity.AccountEntity;
import com.accounts.service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
	
	@Autowired
	AccountService service;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<AccountEntity>> getAccounts() {
		List<AccountEntity> accList = service.getAccountList();
		return new ResponseEntity<List<AccountEntity>>(accList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<AccountEntity> getAccounts(@PathVariable Long id) {
		AccountEntity accList = service.getAccount(id);
		return new ResponseEntity<AccountEntity>(accList, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	@PostAuthorize("hasAnyRole('Admin')")
	public ResponseEntity deleteAccounts(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	@PostAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<Map<String,Object>> createAccount(@RequestBody AccountEntity account) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AccountEntity accountRequest = (AccountEntity) authentication.getPrincipal();
		Map<String, Object> respose = new HashMap<String, Object>();
		if(!accountRequest.getRol().equals("Admin")) {
			respose.put("Message", "Operacion no permitida se requiere un rol Admin para completar el proceso");
			return new ResponseEntity<Map<String,Object>>(respose, HttpStatus.FORBIDDEN);
		}
				
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		AccountEntity acc =service.crearAccount(account, false);
		respose.put("User", acc);
		respose.put("Message", "Usuario creado con exito.");
		return new ResponseEntity<Map<String,Object>>(respose, HttpStatus.CREATED);
		
	}

	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	@PostAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<Map<String,Object>> modifyAccount(@PathVariable Long id, @RequestBody AccountEntity account) throws Exception {
		Map<String, Object> respose = new HashMap<String, Object>();
		account.setId(id);
		account = service.crearAccount(account, true);
		respose.put("User", account);
		respose.put("Message", "Usuario modificado con exito.");
		return new ResponseEntity<Map<String,Object>>(respose, HttpStatus.ACCEPTED);
	}
	
	
	
}
