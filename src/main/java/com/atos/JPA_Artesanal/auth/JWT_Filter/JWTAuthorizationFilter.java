package com.atos.JPA_Artesanal.auth.JWT_Filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.atos.JPA_Artesanal.auth.serviceJWT.JWTService;
import com.atos.JPA_Artesanal.auth.serviceJWT.JWTServiceImpl;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	Logger log = Logger.getLogger("MyLogger");

	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
		if (header == null) {
			if (request.getCookies() != null) {
				for (Cookie c : request.getCookies()) {
					if ("token".equals(c.getName())) {
						header = "Bearer " + c.getValue();
					}
				}
			}
		}

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		logger.info("AUTHORIZATION :" + header);
		UsernamePasswordAuthenticationToken authentication = null;

		if (jwtService.validate(header)) {
			logger.info("EXITO validacion");

			logger.info("USERNAME///////////////" + jwtService.getUsername(header));
			// logger.warn("ROLES///////////////" + jwtService.getRoles(header));

			authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null,
					jwtService.getRoles(header));
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

		// response.addHeader("Access-Control-Allow-Origin", "*");

	}

	protected boolean requiresAuthentication(String header) {

		if (header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}

}
