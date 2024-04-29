package com.accounts.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accounts.entity.AccountEntity;
import com.accounts.service.AccountService;

@Service
public class AccountsDetails {//implements UserDetailsService{
	
//	@Autowired
//	AccountService service;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;

	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AccountEntity accoun =  service.getAccount(username);
//		UserDetails usrDetails = User.withUsername(username)
//				.password(accoun.getPassword())
//				.roles(accoun.getRol()).build();
//		return usrDetails;
////		return new User("Leonard",passwordEncoder.encode("123"), new ArrayList<>());
//	}
	
//	@Override
//	public UserDetails updatePassword(UserDetails user, String newPassword) {
//		 String encodedPassword = passwordEncoder.encode(newPassword);
//
//	        // Crea un nuevo UserDetails con la contraseña actualizada y devuelve el resultado
//	        return new org.springframework.security.core.userdetails.User(
//	            user.getUsername(),
//	            encodedPassword,
//	            user.getAuthorities()
//	        );
//	}

}
