package com.configuration.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
	
	private String secrateKey = "";
	
	public JWTservice(){
		KeyGenerator jeygen;
		
		try {
			
			jeygen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = jeygen.generateKey();
			secrateKey = Base64.getEncoder().encodeToString(sk.getEncoded());
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			
		}
		
		
	}

	public String GenerateToken(String username) {
		Map<String,Object> claims= new HashMap<>();
		
		
 		return Jwts.builder()
 				.claims()
 				.add(claims)
 				.subject(username)
 				.issuedAt(new Date(System.currentTimeMillis()))
 				.expiration(new Date(System.currentTimeMillis()+60*1000))
 				.and()
 				.signWith(getKey())
 				.compact();
	}
	
//	private Key getKey() {
//		byte[] keybyte = Decoders.BASE64.decode(secrateKey);
//		return Keys.hmacShaKeyFor(keybyte);
//	}

	private SecretKey getKey() {
		byte[] keybyte = Decoders.BASE64.decode(secrateKey);
		return Keys.hmacShaKeyFor(keybyte);
	}
	
	/////////////////////////////////////////////////////////////////////////////

	public String extrateUsername(String token) {
		return extractclaim(token,Claims::getSubject);
	}
	
	private <T> T extractclaim(String token , Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String userName = extrateUsername(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

	private boolean isTokenExpired(String token) {

		
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {

		return extractclaim(token, Claims::getExpiration);
		
	}

	
}
