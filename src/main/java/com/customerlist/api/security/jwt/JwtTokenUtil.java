package com.customerlist.api.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	private static final String HEADER_PREFIX = "Bearer ";

	@Value("${mysports.jwt.secret}")
	private String secret;

	@Value("${mysports.jwt.validity.in.seconds}")
	private long tokenValidity;

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	Boolean isTokenExpired(String token) {
		final Date expiration = extractExpiratioDate(token);
		return expiration.before(new Date());
	}

	public Date extractExpiratioDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String extractTokenFromAuthorization(String authorizationHeader) {
		String jwtToken = "";
		if (authorizationHeader == null || !authorizationHeader.startsWith(HEADER_PREFIX)) {
//			if (LoggerFactory.getInstance(this.getClass()).isDebugActive()) {
//				LoggerFactory.getInstance(this.getClass()).debug("No Token in header");
//			}
		} else {
			// get token from Header
			jwtToken = authorizationHeader.substring(7);
		}

		return jwtToken;
	}
}
