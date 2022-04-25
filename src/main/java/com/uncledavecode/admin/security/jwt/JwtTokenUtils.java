package com.uncledavecode.admin.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import com.uncledavecode.admin.services.UserDetailsImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtils {

	private final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

	@Value("${com.uncledavecode.jwt-secret}")
	private String jwtSecret;
	@Value("${com.uncledavecode.jwt-expiration}")
	private Long jwtExpiration;

	@Value("${com.uncledavecode.jwt-expiration-remember}")
	private Long jwtExpirationRemember;

	private Key key;

	@PostConstruct
	public void init() {
		byte[] keyBytes;
		if (jwtSecret != null) {
			keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);

			this.key = Keys.hmacShaKeyFor(keyBytes);
		}
	}

	public String generateJwtToken(Authentication authentication, boolean rememberMe) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		long now = (new Date()).getTime();
		Date validity;

		if (rememberMe) {
			validity = new Date(now + this.jwtExpirationRemember);
		} else {
			validity = new Date(now + this.jwtExpiration);
		}

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(validity)
				.signWith(this.key, SignatureAlgorithm.HS512)
				.compact();
	}

	public boolean isValidJwtToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
