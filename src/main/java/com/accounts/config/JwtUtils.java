package com.accounts.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {
	
	@Value("${jwt.secret:Test}")
	String key;
	
//	public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public String getName(String token) {
		
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//				Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}

}
