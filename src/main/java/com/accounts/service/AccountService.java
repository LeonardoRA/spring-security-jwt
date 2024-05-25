package com.accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.accounts.entity.AccountEntity;
import com.accounts.repository.AccountRepository;

import jakarta.transaction.Transactional;


@Service
public class AccountService {
	
	@Autowired
	AccountRepository repository;
	
	@Transactional
	public AccountEntity crearAccount(AccountEntity account, boolean isUpdate) throws Exception {
		
		//Looking for existing user name
		if(!isUpdate) {
			AccountEntity accountFound =this.getAccount(account.getName());			
			if(accountFound != null)
				throw new Exception("User Name Already exist");	
		}
		
		account = repository.save(account);
		return account;
	}
	
	public List<AccountEntity> getAccountList (){
		List<AccountEntity> list = repository.findAll();
		return list;
	}
	
	public AccountEntity getAccount(Long id) {
		Optional<AccountEntity> account = repository.findById(id);
		return account.get();
	}
	
	public AccountEntity getAccount(String name) {
		AccountEntity account = new AccountEntity(name);
		
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
		
		Example<AccountEntity> exampleAccount = Example.of(account, matcher);
		
		Optional<AccountEntity> accountResult = repository.findOne(exampleAccount);
		
		return accountResult.isPresent()?accountResult.get():null;
	}

	public void delete(Long id) {
		AccountEntity entity = repository.findById(id).get();
		repository.delete(entity);
	}

}
