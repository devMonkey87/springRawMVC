package com.atos.JPA_Artesanal.auth.serviceJWT;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.atos.JPA_Artesanal.auth.JWT_Filter.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {
	Logger log = Logger.getLogger("MyLogger87");

	public static final String SECRET = Base64Utils
			.encodeToString("Alguna.Contraseña.Secreta.Contraseña.123456789123456789".getBytes());

	public static final long EXPIRATION_DATE = 14000000L;
	// public static final long EXPIRATION_DATE = 140000L;

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@Override
	public String create(Authentication auth) throws IOException {

		// String username = ((User) auth.getPrincipal()).getUsername();
		log.warning("USERNAME GUARDADO AUTH:" + auth.getName());

		String username = auth.getName();

		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		log.warning("ROLES GUARDADO AUTH:" + auth.getAuthorities());

		Claims claims = Jwts.claims();

		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

		String token = Jwts.builder().setClaims(claims).setSubject(username)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE)).compact();

		return token;
	}

	@Override
	public boolean validate(String token) {

		try {

			getClaims(token);
			log.warning("ENTRADO EN METODO VALIDATE, LOS CLAIMS SON:" + getClaims(token).toString());

			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(resolve(token)).getBody();

		// log.warning("*******METODO GETCLAIMS DEL TOKEN DEVUELVE:" +
		// claims.toString());

		return claims;
	}

	@Override
	public String getUsername(String token) {
		// TODO Auto-generated method stub
		return getClaims(token).getSubject();
	}

	@Override
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
		Object roles = getClaims(token).get("authorities");
		// System.out.println("AUTHORITIES :" + roles);

		// log.warning("-----MENSAJE DE LOG------ :" + roles.toString());

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		log.warning("***********AUTHORITIES :" + authorities);

		return authorities;
	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			return token.replace(TOKEN_PREFIX, "");
		}
		return null;
	}
}
