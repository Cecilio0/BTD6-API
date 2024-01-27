package com.dread9182.BTD6API.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {
	private final String JWT_SECRET;
	
	public JwtService(@Value("${jwt.secret}") String jwtSecret) {
		JWT_SECRET = jwtSecret;
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigninKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Key getSigninKey() {
		byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String  generateToken(
			UserDetails userDetails
	) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public String  generateToken(
			Map<String, Object> extraClaims,
			UserDetails userDetails
	) {
		return Jwts
				.builder()
				.setClaims(extraClaims) // Setting all extra claims
				.setSubject(userDetails.getUsername()) // Setting as subject the unique part of a user aside from id
				.setIssuedAt(new Date(System.currentTimeMillis())) // Setting creation date
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Setting expiration date, in this case 24 hours
				.signWith(getSigninKey(), SignatureAlgorithm.HS256) // Signing the token and deciding the algorithm to be used
				.compact();
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date(System.currentTimeMillis()));
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
}
