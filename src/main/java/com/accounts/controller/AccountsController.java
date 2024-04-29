package com.accounts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.entity.AccountEntity;
import com.accounts.service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
	
	@Autowired
	AccountService service;
	
	@GetMapping
//	@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<List<AccountEntity>> getAccounts() {
		List<AccountEntity> accList = service.getAccountList();
		return new ResponseEntity<List<AccountEntity>>(accList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountEntity> getAccounts(@PathVariable Long id) {
		AccountEntity accList = service.getAccount(id);
		return new ResponseEntity<AccountEntity>(accList, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity account) throws Exception {
		AccountEntity acc =service.crearAccount(account, false);
		return new ResponseEntity<AccountEntity>(acc, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountEntity> modifyAccount(@PathVariable Long id, @RequestBody AccountEntity account) throws Exception {
		account.setId(id);
		account = service.crearAccount(account, true);
		return new ResponseEntity<AccountEntity>(account, HttpStatus.ACCEPTED);
	}
	
	
	
}
