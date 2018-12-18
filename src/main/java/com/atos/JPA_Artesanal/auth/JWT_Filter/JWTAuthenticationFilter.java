package com.atos.JPA_Artesanal.auth.JWT_Filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.atos.JPA_Artesanal.auth.serviceJWT.JWTService;
import com.atos.JPA_Artesanal.auth.serviceJWT.JWTServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/jwt", "POST"));

		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

//		String username = obtainUsername(request);
//		String password = obtainPassword(request);

//		if (username != null && password != null) {
//			logger.warn("Username desde request parameter (form-data): " + username);
//			logger.warn("Password desde request parameter (form-data): " + password);
//		} else {
		Usuario user = null;
		try {

			user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

			String username = user.getUsername();
			String password = user.getPassword();

			logger.info("Username desde request InputStream (raw): " + username);
			logger.info("Password desde request InputStream (raw): " + password);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

			logger.info(authToken);
			return authenticationManager.authenticate(authToken);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		}
		return null;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = jwtService.create(authResult);

		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);

		// body.put("user", (User) authResult.getPrincipal());
		// body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!",
		// ((User)authResult.getPrincipal()).getUsername()) );
		body.put("mensaje", String.format("Hola %s, perteneciente al Grupo: %s. has iniciado sesión con éxito!",
				authResult.getName(), authResult.getAuthorities()));

		// body.put("mensaje", String.format("Hola has iniciado sesión con éxito!"));

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);

		Cookie cookie = new Cookie("token", token);
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		response.addCookie(cookie);
		response.setContentType("application/json");

//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//		response.addHeader("Access-Control-Allow-Credentials", "true");
//		response.addHeader("Access-Control-Allow-Methods", "GET,POST");
//		response.addHeader("Access-Control-Allow-Headers",
//				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Cache-Control, Pragma");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Error de autenticación: username o password incorrecto!");
		body.put("error", failed.getMessage());

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
	}

}
