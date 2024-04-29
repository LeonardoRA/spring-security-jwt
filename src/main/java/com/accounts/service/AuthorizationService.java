package com.accounts.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Predicate;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accounts.config.JwtUtils;
import com.accounts.entity.AccountEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthorizationService {
	
	@Value("${jwt.secret:Test}")
	private String secret;
	
	@Value("${jwt.expirationDate:60000}")
	long expirationDate;
	
	@Autowired
	AccountService service;
	
//	@Autowired
//	JwtUtils util;
	
	public String generateToken(AccountEntity accountDetails) {
        
		Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationDate);

//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        
        return Jwts.builder()
                .setSubject(accountDetails.getName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .signWith(JwtUtils.key)
                .signWith(getSignInKey())
                .compact();
    }
	
	private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
    public boolean authenticate(String username, String password) {
    	
    	AccountEntity account;
    	account = service.getAccount(username);
    	Predicate<AccountEntity> accountPredicate = p -> p.getPassword().equals(password);
//    	return accountPredicate.test(account);
    return true;
    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity accoun =  service.getAccount(username);
		UserDetails usrDetails = User.withUsername(username)
				.password(accoun.getPassword())
				.roles(accoun.getRol()).build();
		return usrDetails;
	}
	
    	
	

}
